package org.example.o_lim.entity;

import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.example.o_lim.repository.TagRepository;
import org.example.o_lim.repository.TaskRepository;
import org.example.o_lim.repository.UserRepository;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tasks",
       indexes = {
        @Index(name = "idx_tasks_project_status", columnList = "project_id, status")
       })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tasks_project_id"))
    private Project project;

    // 직무명
    @Column(nullable = false, length = 200)
    private String title;

    // 직무내용
    @Lob
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String content;

    // 직무 작성자
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_user", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tasks_created_user"))
    private User createdUser;

    // 직무 현황
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TaskStatus status = TaskStatus.TODO;

    // 직무 중요도
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    private PriorityStatus priority = PriorityStatus.MEDIUM;

    // TaskTag 관계
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch =  FetchType.LAZY)
    private List<TaskTag> taskTags = new ArrayList<>();

    public List<TaskTag> getTaskTags() {
        return taskTags == null ? new ArrayList<>() : taskTags;
    }

    //TaskAssignee 관계
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskAssignees> assignees = new ArrayList<>();
    public List<TaskAssignees> getAssignee() {
        if(assignees == null) {
            assignees = new ArrayList<>();
        }

        return assignees;
    }

    // task 내 comment 출력
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments;

    // 마감일
    @Column(name = "due_date")
    private LocalDate dueDate;

    public Task(Project project, String title, String content, User createdUser,
                TaskStatus status, PriorityStatus priority, LocalDate dueDate
    ) {
        this.project = project;
        this.title = title;
        this.content = content;
        this.createdUser = createdUser;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    // 직무 생성
    public static Task create(Project project, String title, String content, User createdUser,
                        TaskStatus status, PriorityStatus priority, LocalDate dueDate) {

        TaskStatus safeStatus = (status != null) ? status : TaskStatus.TODO;
        PriorityStatus safePriority = (priority != null) ? priority : PriorityStatus.MEDIUM;

        return new Task(project, title, content, createdUser, safeStatus, safePriority, dueDate);
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
    }

    public void addAssignee(User user) {
        if (this.assignees == null) {
            this.assignees = new ArrayList<>();
        }

        boolean alreadyAssigned = this.assignees.stream()
                .anyMatch(assignee -> assignee.getAssignees().equals(user));

        if (alreadyAssigned) {
            return;
        }

        TaskAssignees taskAssignees = new TaskAssignees(this, user);
        this.assignees.add(taskAssignees);
    }

    public void removeAssignee(User user) {
        if (this.assignees == null || user == null) return;

        Long userId = user.getId();
        this.assignees.removeIf(taskAssignees ->
                taskAssignees.getAssignees() != null &&
                taskAssignees.getAssignees().getId().equals(userId));

    }

    public void addTaskTag(TaskTag taskTag) {
        if (this.taskTags == null) {
            this.taskTags = new ArrayList<>();
        }

        Long tagId = taskTag.getTag().getId();

        boolean exists = this.taskTags.stream()
                .map(tt -> tt.getTag().getId())
                .anyMatch(id -> id.equals(tagId));

        if(!exists) {
            taskTag.setTask(this);
            this.taskTags.add(taskTag);
        }
    }

    public void setTitle(String title) { this.title = title;}

    public void setContent(String content) { this.content = content;}

    public void setAssignee(List<Long> assigneeIds, UserRepository userRepository) {
        this.assignees.clear();

        if(assigneeIds == null || assigneeIds.isEmpty()) {
            return;
        }

        for (Long userId : assigneeIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다. ID: " + userId));

            this.assignees.add(new TaskAssignees(this, user));
        }
    }

    public void setStatus(TaskStatus status) { this.status = status; }

    public void setPriority(PriorityStatus priority) { this.priority = priority;}

    public void setTagId(List<Long> tagIds, TagRepository tagRepository, TaskRepository taskRepository) {
        List<TaskTag> oldTaskTags = new ArrayList<>(this.getTaskTags());

        for(TaskTag oldTaskTag: oldTaskTags) {
            oldTaskTag.setTask(null);
            this.taskTags.remove(oldTaskTag);
        }
        taskRepository.flush();

        if(tagIds == null || tagIds.isEmpty()) {
            return;
        }

        Set<Long> addedTagIds = new HashSet<>();

        for (Long tagId : tagIds) {
            if(!addedTagIds.add(tagId)) continue;

            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("태그가 존재하지 않습니다. ID: " + tagId));

            TaskTag taskTag = TaskTag.create(this, tag);
            this.addTaskTag(taskTag);
        }
    }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public void addTag(Tag tag) {
        if (this.taskTags == null) {
            this.taskTags = new ArrayList<>();
        }

        boolean alreadyExists = taskTags.stream()
                .anyMatch(tt -> tt.getTag().getId().equals(tag.getId()));
        if (alreadyExists) return;

        TaskTag taskTag = TaskTag.create(this, tag);
        this.taskTags.add(taskTag);
    }


    public Set<User> getTaskAssigneeUsers() {
        return this.assignees.stream()
                .map(TaskAssignees::getAssignees)
                .collect(Collectors.toSet());
    }

    public void setAssignees(Set<User> users) {
        this.assignees.clear();
        for(User user: users) {
            this.assignees.add(new TaskAssignees(this, user));
        }
    }

}

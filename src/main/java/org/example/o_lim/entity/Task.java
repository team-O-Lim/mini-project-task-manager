package org.example.o_lim.entity;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.example.o_lim.repository.TagRepository;
import org.example.o_lim.repository.UserRepository;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks",
       indexes = {
        @Index(name = "idx_tasks_project_status", columnList = "project_id, status")
       })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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
    private List<TaskAssignees> assignee = new ArrayList<>();

    public List<TaskAssignees> getAssignee() {
        return assignee;
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

    // Task Assignee 연관관계 메서드




    // 직무 생성
    public static Task create(Project project, String title, String content, User createdUser,
                        TaskStatus status, PriorityStatus priority, LocalDate dueDate) {
        Task task = Task.builder()
                .project(project)
                .title(title)
                .content(content)
                .createdUser(createdUser)
                .status(status)
                .priority(priority)
                .dueDate(dueDate)
                .build();

        task.addAssignee(createdUser);

        return task;

    }

    public void update(
            String title, String content, List<Long> longs, TaskStatus status, PriorityStatus priority, LocalDate dueDate
    ) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
    }

    public void addAssignee(User user) {
        if (this.assignee == null) {
            this.assignee = new ArrayList<>();
        }

        boolean alreadyAssigned = this.assignee.stream()
                .anyMatch(assignee -> assignee.getAssignees().equals(user));
        if (alreadyAssigned) {
            return;
        }

        TaskAssignees taskAssignees = new TaskAssignees(this, user);
        this.assignee.add(taskAssignees);
    }

    public void addTaskTag(TaskTag taskTag) {
        if (this.taskTags == null) {
            this.taskTags = new ArrayList<>();
        }
        this.taskTags.add(taskTag);
    }

    public void setTitle(String title) { this.title = title;}

    public void setContent(String content) { this.content = content;}

    public void setAssignee(List<Long> assigneeIds, UserRepository userRepository) {
        if(assigneeIds == null) {
            this.assignee.clear();
            return;
        }

        this.assignee.clear();
        userRepository.flush();

        for (Long userId : assigneeIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다. ID: " + userId));
            TaskAssignees taskAssignees = new TaskAssignees(this, user);
            this.assignee.add(taskAssignees);
        }
    }

    public void setStatus(TaskStatus status) { this.status = status; }

    public void setPriority(PriorityStatus priority) { this.priority = priority;}

    public void setTagId(List<Long> tagIds, TagRepository tagRepository) {
        if(tagIds == null) {
            this.taskTags.clear();
            return;
        }

        this.taskTags.clear();
        tagRepository.flush();
        for (Long tagId : tagIds) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("태그가 존재하지 않습니다. ID: " + tagId));
            TaskTag taskTag = new TaskTag(this, tag);
            this.taskTags.add(taskTag);
        }
    }

    public void removeAssignee(TaskAssignees assigneeToRemove) {
        this.assignee.remove(assigneeToRemove);
        assigneeToRemove.setTask(null);
    }

    public void removeTaskTag(TaskTag tagToRemove) {
        this.taskTags.remove(tagToRemove);
        tagToRemove.setTask(null);
    }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}

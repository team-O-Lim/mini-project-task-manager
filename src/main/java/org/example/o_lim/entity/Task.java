package org.example.o_lim.entity;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskTag> taskTags = new ArrayList<>();

    public void addTaskTag(TaskTag taskTag) {
        taskTags.add(taskTag);
        taskTag.setTask(this);
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
    private LocalDate dueDate;

    // 직무 생성
    public static Task create(Project project, String title, String content, User createdUser,
                        TaskStatus status, PriorityStatus priority, LocalDate dueDate) {
        return Task.builder()
                .project(project)
                .title(title)
                .content(content)
                .createdUser(createdUser)
                .status(status)
                .priority(priority)
                .dueDate(dueDate)
                .build();
    }

    public void update(String title, String content, TaskStatus status, PriorityStatus priority, LocalDate dueDate) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

package org.example.o_lim.entity;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.base.BaseTimeEntity;
import org.hibernate.annotations.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks",
       indexes = {
        @Index(name = "idx_task_project_status", columnList = "project_id, status"),
        @Index(name = "idx_task_assignee_due", columnList = "assigned_user, due_date")
       })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "comments")
@Builder
public class Task extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_project"))
    private Project project;

    @Comment("직무")
    @Column(nullable = false, length = 200)
    private String title;

    @Comment("직무 내용")
    @Lob
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Comment("직무 작성자")
    @JoinColumn(name = "created_user", nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_created_user"))
    private User createdUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Comment("직무 담당자")
    @JoinColumn(name = "assigned_user", nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_assignee"))
    private User assignedUser;


    // 직무 현황
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TaskStatus status = TaskStatus.TODO;

    // 직무 중요도
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    private PriorityStatus priority = PriorityStatus.MEDIUM;

    private LocalDate dueDate;

    // 직무 생성
    public void newTask(String title, String content, User createdUser) {
        this.title = title;
        this.content = content;
        this.createdUser = createdUser;
    }



}

package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_assignees")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskAssignees {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id",
        foreignKey = @ForeignKey(name = "fk_task_assignees_task"),
        nullable = false
    )
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignee_id",
        foreignKey = @ForeignKey(name = "fk_task_assignees_assignee")
    )
    private User assignees;
}

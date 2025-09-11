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
    @EmbeddedId
    private TaskAssigneesId id;

    @MapsId("taskId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "task_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_assignees_task")
    )
    private Task task;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_assignees_user")
    )
    private User user;

    public TaskAssignees(Task task, User user) {
        this.task = task;
        this.user = user;

        Long taskId = task.getId();
        Long userId = user.getId();
        this.id = new TaskAssigneesId(taskId, userId);
    }
}

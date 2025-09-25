package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_tags",
        uniqueConstraints = @UniqueConstraint(name = "uk_task_tags_task_id_tag_id", columnNames = {"task_id", "tag_id"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id",
            foreignKey = @ForeignKey(name = "fk_task_tags_task_id"),
            nullable = false
    )
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id",
            foreignKey = @ForeignKey(name = "fk_task_tags_tag_id")
    )
    private Tag tag;

    public static TaskTag create(Task task, Tag tag) {
        TaskTag taskTag = new TaskTag();
        taskTag.task = task;
        taskTag.tag = tag;

        return taskTag;
    }

    public void setTask(Task task) {
//        if (this.task != null) {
//            this.task.getAssignee().remove(this);
//        }
//        this.task
        this.task = task;
    }

    public TaskTag(Task task, Tag tag) {
        this.task = task;
        this.tag = tag;
    }
}


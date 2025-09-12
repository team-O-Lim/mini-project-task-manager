package org.example.o_lim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskTagId implements Serializable {

    @Column(name = "task_id", nullable = false)
    private String taskId;

    @Column(name = "tag_id", nullable = false)
    private String tagId;

    public TaskTagId(String taskId, String tagId) {
        this.taskId = taskId;
        this.tagId = tagId;
    }
}

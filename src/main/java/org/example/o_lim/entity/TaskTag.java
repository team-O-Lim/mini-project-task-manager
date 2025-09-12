package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_tag",
        uniqueConstraints = @UniqueConstraint(name = "uk_task_tag_task_id_tag_id", columnNames = {"task_id", "tag_id"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id",
            foreignKey = @ForeignKey(name = "fk_task_tag_task"),
            nullable = false
    )
    private Task task;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id",
            foreignKey = @ForeignKey(name = "fk_task_tag_tag")
    )
    private Tag tag;

}


package org.example.o_lim.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long authorId;

    @Column(name = "content", nullable = false)
    private String content;

}

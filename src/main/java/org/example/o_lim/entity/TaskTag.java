package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

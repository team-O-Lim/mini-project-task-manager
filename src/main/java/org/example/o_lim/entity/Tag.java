package org.example.o_lim.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "tags",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_tags_project_id_name", columnNames = {"project_id", "name"}),
                @UniqueConstraint(name = "uk_tags_project_id_color", columnNames = {"project_id", "color"})
        }
)
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tag_project"))
    private Project project;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "color", nullable = false)
    private String color;

}

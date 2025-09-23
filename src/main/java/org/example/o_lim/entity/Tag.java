package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(
        name = "tags",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_tags_project_id_name", columnNames = {"project_id", "name"}),
                @UniqueConstraint(name = "uk_tags_project_id_color", columnNames = {"project_id", "color"})
        },
        indexes = {
                @Index(name = "idx_tags_project_id", columnList = "project_id")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Comment("태그 고유키")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("프로젝트 외래키")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tags_project_id"))
    private Project project;

    @Comment("태그명")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("태그색")
    @Column(name = "color", nullable = false)
    private String color;

    public Tag(Project project, String name, String color) {
        this.project = project;
        this.name = name;
        this.color = color;
    }

    public static Tag create(Project project, String name, String color) {
        return new Tag(project, name, color);
    }

}

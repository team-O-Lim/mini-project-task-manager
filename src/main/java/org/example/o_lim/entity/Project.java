package org.example.o_lim.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.o_lim.entity.base.BaseTimeEntity;

import jakarta.persistence.*;


@Entity
@Table( name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Project extends BaseTimeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
    name = "admin_id",
    nullable = false, 
    foreignKey = @ForeignKey(name = "fk_projects_admin_id")
    )
  private User admin;

  @Column(nullable = false, length = 150)
  private String title;

  @Column(nullable = false, length = 255)
  private String description;

  public static Project create(User admin, String title, String description) {
    return Project.builder()
            .admin(admin)
            .title(title)
            .description(description)
            .build();
  }

  public void update(String title, String description) {
    this.title = title;
    this.description = description;
  }
}

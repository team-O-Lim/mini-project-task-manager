package org.example.o_lim.entity;

import org.example.o_lim.entity.base.BaseTimeEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table( name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Project extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
    name = "admin_id",
    nullable = false, 
    foreignKey = @ForeignKey(name = "fk_project_admin")
    )
  private User admin;

  @Column(nullable = false, length = 150)
  private String title;

  @Column(nullable = false, length = 255)
  private String description;

}

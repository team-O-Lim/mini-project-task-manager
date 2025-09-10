package org.example.o_lim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.o_lim.common.enums.RoleType;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor
public class Role {

    @Id @Column(name = "role_name", nullable = false)
    private RoleType name;

    public Role(RoleType name) {
        this.name = name;
    }
}

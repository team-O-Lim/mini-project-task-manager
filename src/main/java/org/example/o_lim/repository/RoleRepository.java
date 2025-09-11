package org.example.o_lim.repository;

import org.example.o_lim.common.enums.RoleType;
import org.example.o_lim.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, RoleType> {
}

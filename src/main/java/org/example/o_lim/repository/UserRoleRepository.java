package org.example.o_lim.repository;

import org.example.o_lim.entity.UserRole;
import org.example.o_lim.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByIdUserId(Long userId);
}


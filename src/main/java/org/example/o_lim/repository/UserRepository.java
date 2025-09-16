package org.example.o_lim.repository;

import org.example.o_lim.common.enums.RoleType;
import org.example.o_lim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public interface UserWithRolesProjection {
        String getUsername();
        String getUserNickname();
        String getUserLoginId();
        Set<RoleType> getRoleTypeRoleName();
    }

    @Query(value = """
        SELECT
            u.name as Username,
            u.nickname as UserNickname,
            u.login_id as UserLoginId,
            r.role_name as RoleTypeRoleName
        FROM
            users u
            LEFT JOIN user_roles r
            ON u.id = r.user_id            
    """, nativeQuery = true)
    List<UserWithRolesProjection> findAllUsersWithRole_Native();
}

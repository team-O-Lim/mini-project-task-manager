package org.example.o_lim.dto.admin.response;

import org.example.o_lim.common.enums.RoleType;

import java.time.LocalDateTime;
import java.util.Set;

public record RemoveRoleResponseDto(
        Long userId,
        String loginId,
        RoleType removed,
        Set<RoleType> roles,
        LocalDateTime updatedAt
) {
}

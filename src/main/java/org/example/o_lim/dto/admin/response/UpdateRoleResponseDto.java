package org.example.o_lim.dto.admin.response;

import org.example.o_lim.common.enums.RoleType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public record UpdateRoleResponseDto(
        Long userId,
        String loginId,
        Set<RoleType> roles,
        LocalDateTime updatedAt
) {
}

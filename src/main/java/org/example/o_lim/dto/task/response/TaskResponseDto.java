package org.example.o_lim.dto.task.response;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        Long projectId,
        String title,
        String content,
        Long createdUserId,
        Long assignedUserId,
        String status,
        String priority,
        LocalDateTime dueDate
) {}
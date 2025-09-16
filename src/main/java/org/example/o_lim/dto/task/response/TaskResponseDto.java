package org.example.o_lim.dto.task.response;

import org.example.o_lim.dto.comment.response.CommentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record TaskResponseDto(
        Long id,
        Long projectId,
        String title,
        String content,
        Long createdUserId,
        String status,
        String priority,
        LocalDateTime dueDate,
        List<CommentResponseDto> comments
) {}
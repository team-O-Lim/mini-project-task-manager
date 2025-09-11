package org.example.o_lim.dto.task.response;

import org.example.o_lim.dto.comment.response.CommentDetailResponse;

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
        List<CommentDetailResponse> comments
) {}
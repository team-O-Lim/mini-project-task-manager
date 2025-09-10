package org.example.o_lim.dto.comment.response;

import java.time.LocalDateTime;

public record CommentListResponse(
        Long id,
        Long taskId,
        Long authorId,
        String content,
        LocalDateTime createdAt
) {
}

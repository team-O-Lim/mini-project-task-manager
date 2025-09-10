package org.example.o_lim.dto.comment.response;

import jakarta.persistence.Column;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public record CommentDetailResponse(
        Long id,
        Long taskId,
        Long authorId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updateAt0
) {
}

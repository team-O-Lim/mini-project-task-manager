package org.example.o_lim.dto.comment.response;

import org.example.o_lim.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponseDto(

        Long authorId,
        String content,
        LocalDateTime createdAt
) {
    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getAuthor().getId(),
                comment.getContent(),
                comment.getCreatedAt());
    }
}

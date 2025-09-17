package org.example.o_lim.dto.comment.response;

import org.example.o_lim.entity.Comment;
import org.example.o_lim.entity.User;

import java.time.LocalDateTime;

public record CommentResponseDto(

        String authorName,
        String content,
        LocalDateTime createdAt
) {
    public static CommentResponseDto from(Comment comment, User user) {
        return new CommentResponseDto(
                user.getNickname(),
                comment.getContent(),
                comment.getCreatedAt());
    }
}

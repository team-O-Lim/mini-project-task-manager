package org.example.o_lim.service;

import jakarta.validation.constraints.Positive;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.comment.request.CommentRequestDto;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.security.UserPrincipal;

public interface CommentService {

    ResponseDto<CommentResponseDto> createComment(UserPrincipal userPrincipal, CommentRequestDto req);

    ResponseDto<CommentResponseDto> deleteComment(UserPrincipal userPrincipal, @Positive(message = "1 이상의 정수여야 합니다.") Long taskId, @Positive(message = "1 이상의 정수여야 합니다.") Long commentId);

    ResponseDto<CommentResponseDto> getAllCommentByCreatedAtDesc(@Positive(message = "1 이상의 정수여야 합니다.") Long taskId);
}

package org.example.o_lim.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.comment.request.CommentRequestDto;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.security.UserPrincipal;

import java.util.List;

public interface CommentService {

    ResponseDto<CommentResponseDto> createComment(UserPrincipal principal, @Valid CommentRequestDto request,Long taskId);
    ResponseDto<CommentResponseDto> deleteComment(UserPrincipal principal, Long taskId, Long commentId);
}

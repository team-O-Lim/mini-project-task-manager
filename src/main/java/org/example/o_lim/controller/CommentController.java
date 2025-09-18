package org.example.o_lim.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.comment.request.CommentRequestDto;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.CommentService;
import org.hibernate.annotations.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
// "/api/v1/tasks/{taskId}/comments"
@RequestMapping(ApiMappingPattern.Comments.ROOT)
public class CommentController {

    private final CommentService commentService;

    // 생성 "/api/v1/tasks/{taskId}/comments"
    @PostMapping
    public ResponseEntity<ResponseDto<CommentResponseDto>> createComment(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody CommentRequestDto request,
            @PathVariable Long taskId
            ) {
        ResponseDto<CommentResponseDto> response = commentService.createComment(principal, request, taskId);

        return ResponseEntity.ok().body(response);
    }

    // 삭제 "/api/v1/tasks/{taskId}/comments/{commentId}"
    @DeleteMapping(ApiMappingPattern.Comments.BY_ID)
    public ResponseEntity<ResponseDto<CommentResponseDto>> deleteComment(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long taskId,
            @PathVariable Long commentId
            ) {
        ResponseDto<CommentResponseDto> response = commentService.deleteComment(principal, taskId, commentId);

        return  ResponseEntity.ok().body(response);
    }
}
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
@Comment("기본 경로 값 = /api/v1/tasks/{taskId}/comments")
@RequestMapping(ApiMappingPattern.Comments.ROOT)
public class CommentController {

    private final CommentService commentService;

    @Comment("댓글생성 = ROOT")
    @PostMapping
    public ResponseEntity<ResponseDto<CommentResponseDto>> createComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody CommentRequestDto request,
            @PathVariable Long taskId
            ) {
        ResponseDto<CommentResponseDto> response = commentService.createComment(userPrincipal, request, taskId);

        return ResponseEntity.ok().body(response);
    }

    @Comment("특정 task 내 comment 최신순 댓글조회 = ROOT")
    @GetMapping
    public ResponseEntity<ResponseDto<List<CommentResponseDto>>> getAllCommentByCreatedAtDesc(
            @PathVariable Long taskId
            ) {
        ResponseDto<List<CommentResponseDto>> response = commentService.getAllCommentByCreatedAtDesc(taskId);

        return ResponseEntity.ok().body(response);
    }

    @Comment("댓글삭제 = ROOT + /{commentId}")
    @DeleteMapping(ApiMappingPattern.Comments.BY_ID)
    public ResponseEntity<ResponseDto<CommentResponseDto>> deleteComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long taskId,
            @PathVariable Long commentId
            ) {
        ResponseDto<CommentResponseDto> response = commentService.deleteComment(userPrincipal, taskId, commentId);

        return  ResponseEntity.ok().body(response);
    }
}
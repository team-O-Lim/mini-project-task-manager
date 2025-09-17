package org.example.o_lim.controller;


import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.comment.request.CommentRequestDto;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1"
@RequestMapping(ApiMappingPattern.Comments.ROOT)
public class CommentController {

    private final CommentService commentService;

    // 생성 "/api/v1/tasks/{taskId}/comments"
//    @PostMapping(ApiMappingPattern.Comments.ROOT)
//    public ResponseEntity<ResponseDto<CommentResponseDto>> createComment(
//            @AuthenticationPrincipal UserPrincipal userPrincipal,
//            @RequestBody CommentRequestDto req
//            ) {
//        ResponseDto<CommentResponseDto> response = commentService.createComment(userPrincipal, req);
//
//        return ResponseEntity.ok().body(response);
//    }
//
//    // 삭제 "/api/v1/tasks/{taskId}/comments/{commentId}"
//    @DeleteMapping(ApiMappingPattern.Comments.BY_ID)
//    public ResponseEntity<ResponseDto<CommentResponseDto>> deleteComment(
//            @AuthenticationPrincipal UserPrincipal userPrincipal,
//            @PathVariable("authorId") @Positive(message = "1 이상의 정수여야 합니다.") Long authorId
//    ) {
//        ResponseDto<CommentResponseDto> response = commentService.deleteComment(userPrincipal, authorId);
//        return  ResponseEntity.ok().body(response);
//
//    }
//    // 특정 task 내 comment 최신순 조회 "/api/v1/tasks/{taskId}/comments"
//    @GetMapping(ApiMappingPattern.Comments.BY_ID)
//    public ResponseEntity<ResponseDto<CommentResponseDto>> getAllCommentByCreatedAtDesc() {
//        ResponseDto<CommentResponseDto> response = commentService.getAllCommentByCreatedAtDesc();
//        return ResponseEntity.ok().body(response);
//    }

}
package org.example.o_lim.controller;


import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1"
@RequestMapping(ApiMappingPattern.Comments.ROOT)
public class CommentController {

//    private final CommentService commentService;

    // 생성 "/api/v1/tasks/{taskId}/comments"
//    @PostMapping(ApiMappingPattern.Comments.ROOT)
//    public ResponseEntity<ResponseDto<CommentDetailResponse>> createComment(
//            @AuthenticationPrincipal UserPrincipal userPrincipal,
//            @RequestBody CommentCreateRequest req
//            ) {
//        ResponseDto<CommentDetailResponse> responseDto = commentService.createComment(userPrincipal, req);
//
//        return ResponseEntity.ok().body(responseDto);
//    }

    // 삭제 "/api/v1/tasks/{taskId}/comments/{commentId}"
//    @DeleteMapping(ApiMappingPattern.Comments.DELETE)

    // 특정 task 내 comment 최신순 조회 "/api/v1/tasks/{taskId}/comments"
//    @GetMapping(ApiMappingPattern.Comments.SEARCH_COMMENT_IN_TASK_BY_NEW)

}
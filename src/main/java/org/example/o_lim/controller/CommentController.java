package org.example.o_lim.controller;


import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1"
@RequestMapping(ApiMappingPattern.Comments.ROOT)
public class CommentController {

    // 생성 "/api/v1/tasks/{taskId}/comments"
    @PostMapping(ApiMappingPattern.Comments.ROOT)

    // 삭제 "/api/v1/tasks/{taskId}/comments/{commentId}"
    @DeleteMapping(ApiMappingPattern.Comments.DELETE)

    // 특정 task 내 comment 최신순 조회 "/api/v1/tasks/{taskId}/comments"
    @GetMapping(ApiMappingPattern.Comments.SEARCH_COMMENT_IN_TASK_BY_NEW)

}
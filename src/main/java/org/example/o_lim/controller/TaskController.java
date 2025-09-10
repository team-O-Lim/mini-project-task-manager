package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "api/v1"
@RequestMapping(ApiMappingPattern.BASE)
public class TaskController {

    // 생성 "/api/v1/projects/{projectId}/tasks"
//     @PostMapping(ApiMappingPattern.Tasks.ROOT)

    // 전체 필터링 조회 "/api/v1/projects/{projectId}/tasks/{담당자FK}"
//     @GetMapping(ApiMappingPattern.Tasks.ALL_BY_ID)

    // 단건 조회 "/api/v1/tasks/{taskId}"
//     @GetMapping(ApiMappingPattern.Tasks.BY_ID)

    // 수정 "/api/v1/tasks/{taskId}"
//    @PutMapping(ApiMappingPattern.Tasks.BY_ID)

    // 삭제 "/api/v1/tasks/{taskId}"
//    @DeleteMapping(ApiMappingPattern.Tasks.BY_ID)



}

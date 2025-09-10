package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1/projects"
@RequestMapping(ApiMappingPattern.Projects.ROOT)
public class ProjectController {

    // 생성 "/api/v1/projects"
    @PostMapping

    // 전체 조회 (내림차순) "/api/v1/projects"
    @GetMapping

    // task 순 조회 "/api/v1/projects/task-desc"
    @GetMapping(ApiMappingPattern.Projects.SEARCH_BY_TASK_DESC)

    // 단건 조회 "/api/v1/projects/{projectId}"
    @GetMapping(ApiMappingPattern.Projects.BY_ID)

    // 수정 "/api/v1/projects/{projectId}"
    @PutMapping(ApiMappingPattern.Projects.BY_ID)

    // 삭제 "/api/v1/projects/{projectId}"
    @DeleteMapping(ApiMappingPattern.Projects.BY_ID)

}

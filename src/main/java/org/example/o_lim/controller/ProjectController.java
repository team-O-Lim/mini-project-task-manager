package org.example.o_lim.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.project.request.ProjectCreateRequestDto;
import org.example.o_lim.dto.project.response.ProjectDetailResponseDto;
import org.example.o_lim.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1/projects"
@RequestMapping(ApiMappingPattern.Projects.ROOT)
public class ProjectController {
    private final ProjectService projectService;

    // 생성 "/api/v1/projects"
    @PostMapping
    public ResponseEntity<ResponseDto<ProjectDetailResponseDto>> createProject(
            @Valid @RequestBody ProjectCreateRequestDto dto
            ) {
        ResponseDto<ProjectDetailResponseDto> response = projectService.createProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 전체 조회 (내림차순) "/api/v1/projects"
    @GetMapping(ApiMappingPattern.Projects.BY_ID)
    public ResponseEntity<ResponseDto<ProjectDetailResponseDto>> getProjectById(
            @PathVariable Long projectId
    ) {
        ResponseDto<ProjectDetailResponseDto> response = projectService.getProjectById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // task 순 조회 "/api/v1/projects/task-desc"
//    @GetMapping(ApiMappingPattern.Projects.SEARCH_BY_TASK_DESC)

    // 단건 조회 "/api/v1/projects/{projectId}"
//    @GetMapping(ApiMappingPattern.Projects.BY_ID)

    // 수정 "/api/v1/projects/{projectId}"
//    @PutMapping(ApiMappingPattern.Projects.BY_ID)

    // 삭제 "/api/v1/projects/{projectId}"
//    @DeleteMapping(ApiMappingPattern.Projects.BY_ID)

}

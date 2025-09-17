package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.task.request.TaskCreateRequestDto;
import org.example.o_lim.dto.task.request.TaskDeleteRequestDto;
import org.example.o_lim.dto.task.request.TaskSearchRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateRequestDto;
import org.example.o_lim.dto.task.response.TaskCreateResponseDto;
import org.example.o_lim.dto.task.response.TaskDetailResponseDto;
import org.example.o_lim.dto.task.response.TaskSearchResponseDto;
import org.example.o_lim.dto.task.response.TaskUpdateResponseDto;
import org.example.o_lim.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
// "api/v1"
@RequestMapping(ApiMappingPattern.BASE)
public class TaskController {
    private final TaskService taskService;

    // 생성 "/api/v1/projects/{projectId}/tasks"
    @PostMapping(ApiMappingPattern.Tasks.ROOT)
    public ResponseEntity<ResponseDto<TaskCreateResponseDto>> createTask(
            @RequestBody TaskCreateRequestDto req,
            @AuthenticationPrincipal UserPrincipal user
    ) { ResponseDto<TaskCreateResponseDto> response = taskService.createTask(req, user);
        return ResponseEntity.ok().body(response);
    }

    // 전체 조회 "/api/v1/projects/{projectId}/tasks/{담당자FK}"
    @GetMapping(ApiMappingPattern.Tasks.ALL_BY_ID)
    public ResponseEntity<ResponseDto<List<TaskSearchResponseDto>>> getAllTasks() {
        ResponseDto<List<TaskSearchResponseDto>> response = taskService.getAllTasks();
        return ResponseEntity.ok().body(response);
    }

    // 프로젝트 내 Task 리스트 출력
    @GetMapping


    // 단건 조회- 프로젝트 내 특정 Task 출력 "/api/v1/tasks/{taskId}"
//    @GetMapping(ApiMappingPattern.Tasks.BY_ID)
//    public ResponseEntity<ResponseDto<>> getTask(){}
//    @GetMapping(ApiMappingPattern.Tasks.BY_ID)
//    public ResponseEntity<ResponseDto<TaskDetailResponseDto>> getTaskById(
//            @PathVariable Long taskId,
//            @AuthenticationPrincipal UserPrincipal user,
//            @RequestParam(required = false) Long createUserId,
//            @RequestParam(required = false) TaskStatus status,
//            @RequestParam(required = false) PriorityStatus priority,
//            @RequestParam(required = false)
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate from,
//            @RequestParam(required = false)
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDate to
//            ) {
//        ResponseDto<TaskDetailResponseDto> response = taskService.getTask(taskId, req, user, createUserId ,status, priority, from, to);
//        return ResponseEntity.ok().body(response);
//    }

    // 조회- 프로젝트 내 상세 조회기능(특정 값 검색 ex-중요도, 진행상태, 기간)

    // 수정 "/api/v1/tasks/{taskId}"
    @PutMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<ResponseDto<TaskUpdateResponseDto>> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskUpdateRequestDto req,
            @AuthenticationPrincipal UserPrincipal user
    ) {
        ResponseDto<TaskUpdateResponseDto> response = taskService.updateTask(taskId, req, user);
        return ResponseEntity.ok().body(response);
    }

    // 삭제 "/api/v1/tasks/{taskId}"
    @DeleteMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<ResponseDto<Void>> deleteTask(
            @PathVariable Long taskId, @RequestBody TaskDeleteRequestDto req) {
        taskService.deleteTask(taskId, req);
        return ResponseEntity.ok().build();
    }

}

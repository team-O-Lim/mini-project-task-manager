package org.example.o_lim.controller;

import org.example.o_lim.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.task.request.TaskCreateRequestDto;
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

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
// "api/v1"
@RequestMapping(ApiMappingPattern.Tasks.ROOT)
public class TaskController {
    private final TaskService taskService;

    // 생성
    @PostMapping
    public ResponseEntity<ResponseDto<TaskCreateResponseDto>> createTask(
            @RequestBody TaskCreateRequestDto req,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) { ResponseDto<TaskCreateResponseDto> response = taskService.createTask(req, userPrincipal);
        return ResponseEntity.ok().body(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<TaskSearchResponseDto>>> getAllTasks() {
        ResponseDto<List<TaskSearchResponseDto>> response = taskService.getAllTasks();
        return ResponseEntity.ok().body(response);
    }

    // 단건 조회
    @GetMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<ResponseDto<TaskDetailResponseDto>> getTaskById(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long taskId
    ) {
        ResponseDto<TaskDetailResponseDto> response = taskService.getTaskById(userPrincipal, taskId);
        return ResponseEntity.ok().body(response);
    }

    // 특정 task 작성자 기준 필터링 조회
    @GetMapping(ApiMappingPattern.Tasks.FILTER_CREATED_USER)
    public ResponseEntity<ResponseDto<List<TaskDetailResponseDto>>> getCreatedUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long createdUser
            ) {
        ResponseDto<List<TaskDetailResponseDto>> response = taskService.getCreatedUser(userPrincipal, createdUser);
        return ResponseEntity.ok().body(response);
    }

    // 검색 조회
    @GetMapping(ApiMappingPattern.Tasks.SEARCH)
    public ResponseEntity<ResponseDto<List<TaskDetailResponseDto>>> searchTasks(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(required = false) Long createUserId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) PriorityStatus priority,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate to
    ) {
        ResponseDto<List<TaskDetailResponseDto>> response
                = taskService.searchTasks(userPrincipal, createUserId, status, priority, from, to);
        return ResponseEntity.ok().body(response);
    }


    // 수정
    @PutMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<ResponseDto<TaskUpdateResponseDto>> updateTask(
            @PathVariable Long taskId,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody TaskUpdateRequestDto req
    ) {
        ResponseDto<TaskUpdateResponseDto> response = taskService.updateTask(userPrincipal, taskId, req);
        return ResponseEntity.ok().body(response);
    }

    // 삭제
    @DeleteMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<ResponseDto<Void>> deleteTask(
            @PathVariable Long taskId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        taskService.deleteTask(taskId, userPrincipal);
        return ResponseEntity.ok().build();
    }

}

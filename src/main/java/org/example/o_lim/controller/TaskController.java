package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.task.request.TaskSearchRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateRequestDto;
import org.example.o_lim.dto.task.response.TaskResponseDto;
import org.example.o_lim.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequiredArgsConstructor
// "api/v1"
@RequestMapping(ApiMappingPattern.BASE)
public class TaskController {
    private final TaskService taskService;

    // 생성 "/api/v1/projects/{projectId}/tasks"
    @PostMapping(ApiMappingPattern.Tasks.ROOT)
    public ResponseEntity<TaskResponseDto> createTask(@PathVariable Long projectId, @RequestBody TaskRequestDto requestDto, @AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(taskService.createTask());
    }

    // 전체 필터링 조회 "/api/v1/projects/{projectId}/tasks/{담당자FK}"
    @GetMapping(ApiMappingPattern.Tasks.ALL_BY_ID)
    public ResponseEntity<List<TaskResponseDto>> getTasksByAssignee(@PathVariable Long projectId, @PathVariable Long assigneeId) {
        return ResponseEntity.ok(taskService.getTasksByAssignee());
    }

    // 단건 조회 "/api/v1/tasks/{taskId}"
    @GetMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<TaskSearchRequestDto> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    // 수정 "/api/v1/tasks/{taskId}"
    @PutMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<TaskUpdateRequestDto> updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDto requestDto, @AuthenticationPrincipal UserPrincipal user) {
        taskService.updateTask(taskId, requestDto, user);
        return ResponseEntity.ok().build();
    }

    // 삭제 "/api/v1/tasks/{taskId}"
    @DeleteMapping(ApiMappingPattern.Tasks.BY_ID)
    public ResponseEntity<TaskDeleteRequestDto> deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal UserPrincipal user) {
        taskService.deleteTask(taskId, user);
        return ResponseEntity.ok().build();
    }



}

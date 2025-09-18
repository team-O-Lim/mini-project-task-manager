package org.example.o_lim.service;

import jakarta.validation.Valid;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.task.request.TaskCreateRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateRequestDto;
import org.example.o_lim.dto.task.response.TaskCreateResponseDto;
import org.example.o_lim.dto.task.response.TaskDetailResponseDto;
import org.example.o_lim.dto.task.response.TaskSearchResponseDto;
import org.example.o_lim.security.UserPrincipal;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    ResponseDto<TaskCreateResponseDto> createTask(UserPrincipal principal, TaskCreateRequestDto request);

    ResponseDto<List<TaskSearchResponseDto>> getAllTasks();

    ResponseDto<TaskDetailResponseDto> getTaskById(Long taskId);

    ResponseDto<List<TaskDetailResponseDto>> getCreatedUser(Long createdUser);

    ResponseDto<List<TaskDetailResponseDto>> searchTasks(Long createUserId, TaskStatus status, PriorityStatus priority, LocalDate from, LocalDate to);

    ResponseDto<TaskDetailResponseDto> updateTask(UserPrincipal principal, Long taskId, @Valid TaskUpdateRequestDto request);

    ResponseDto<Void> deleteTask(Long taskId, UserPrincipal principal);



}

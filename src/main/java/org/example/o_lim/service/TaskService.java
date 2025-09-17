package org.example.o_lim.service;

import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.task.request.TaskCreateRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateRequestDto;
import org.example.o_lim.dto.task.response.TaskCreateResponseDto;
import org.example.o_lim.dto.task.response.TaskDetailResponseDto;
import org.example.o_lim.dto.task.response.TaskSearchResponseDto;
import org.example.o_lim.dto.task.response.TaskUpdateResponseDto;
import org.example.o_lim.security.UserPrincipal;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    ResponseDto<TaskCreateResponseDto> createTask(TaskCreateRequestDto req, UserPrincipal userPrincipal);

    ResponseDto<List<TaskSearchResponseDto>> getAllTasks();

    ResponseDto<TaskDetailResponseDto> getTaskById(UserPrincipal userPrincipal, Long taskId);

    ResponseDto<List<TaskDetailResponseDto>> getCreatedUser(UserPrincipal userPrincipal, Long createdUser);

    ResponseDto<List<TaskDetailResponseDto>> searchTasks(UserPrincipal userPrincipal, Long createUserId, TaskStatus status, PriorityStatus priority, LocalDate from, LocalDate to);

    ResponseDto<TaskUpdateResponseDto> updateTask(UserPrincipal userPrincipal, Long taskId, TaskUpdateRequestDto req);

    void deleteTask(Long taskId, UserPrincipal userPrincipal);
}

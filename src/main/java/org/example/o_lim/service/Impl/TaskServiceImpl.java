package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
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
import org.example.o_lim.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Override
    public ResponseDto<TaskCreateResponseDto> createTask(TaskCreateRequestDto req, UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public ResponseDto<List<TaskSearchResponseDto>> getAllTasks() {
        return null;
    }

    @Override
    public ResponseDto<TaskDetailResponseDto> getTaskById(UserPrincipal userPrincipal, Long taskId) {
        return null;
    }

    @Override
    public ResponseDto<List<TaskDetailResponseDto>> getCreatedUser(UserPrincipal userPrincipal, Long createdUser) {
        return null;
    }

    @Override
    public ResponseDto<List<TaskDetailResponseDto>> searchTasks(UserPrincipal userPrincipal, Long createUserId, TaskStatus status, PriorityStatus priority, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public ResponseDto<TaskUpdateResponseDto> updateTask(UserPrincipal userPrincipal, Long taskId, TaskUpdateRequestDto req) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId, UserPrincipal userPrincipal) {

    }
}

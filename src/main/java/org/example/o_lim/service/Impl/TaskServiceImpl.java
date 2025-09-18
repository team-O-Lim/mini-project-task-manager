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
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    @Override
    public ResponseDto<TaskCreateResponseDto> createTask(UserPrincipal principal, TaskCreateRequestDto request) {
        return null;
    }

    @Override
    @Transactional
    public ResponseDto<List<TaskSearchResponseDto>> getAllTasks() {
        return null;
    }

    @Override
    public ResponseDto<TaskDetailResponseDto> getTaskById(Long taskId) {
        return null;
    }

    @Override
    public ResponseDto<List<TaskDetailResponseDto>> getCreatedUser(Long createdUser) {
        return null;
    }

    @Override
    public ResponseDto<List<TaskDetailResponseDto>> searchTasks(Long createUserId, TaskStatus status, PriorityStatus priority, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    @Transactional
    public ResponseDto<TaskDetailResponseDto> updateTask(UserPrincipal principal, Long taskId, TaskUpdateRequestDto request) {
        return null;
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteTask(Long taskId, UserPrincipal principal) {

        return null;
    }

}



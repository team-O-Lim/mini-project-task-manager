package org.example.o_lim.service.Impl;

import io.jsonwebtoken.security.SecurityException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.task.request.TaskCreateRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateRequestDto;
import org.example.o_lim.dto.task.response.TaskCreateResponseDto;
import org.example.o_lim.dto.task.response.TaskDetailResponseDto;
import org.example.o_lim.dto.task.response.TaskSearchResponseDto;
import org.example.o_lim.entity.*;
import org.example.o_lim.repository.*;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final TaskTagRepository taskTagRepository;

    private TaskStatus parseTaskStatus(String status) {
        if (status == null || status.isBlank()) {
            return TaskStatus.TODO;
        }
        try {
            return TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않는 상태 값입니다: " + status);
        }
    }

    private PriorityStatus parsePriorityStatus(String status) {
        if (status == null || status.isBlank()) {
            return PriorityStatus.MEDIUM;
        }
        try {
            return PriorityStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않는 우선 순위 값입니다: " + status);
        }
    }


    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseDto<TaskCreateResponseDto> createTask(
            Long projectId, UserPrincipal principal, TaskCreateRequestDto request
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        boolean isTask = taskRepository.existsByProjectIdAndTitle(project.getId(), request.title());
        if (isTask) {
            throw new IllegalArgumentException("해당 프로젝트 내 테스트가 중복됩니다.");
        }
        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Task task = Task.create(
                project,
                request.title(),
                request.content(),
                user,
                parseTaskStatus(request.status()),
                parsePriorityStatus(request.priority()),
                request.dueDate()
        );
        taskRepository.save(task);

        if (request.tagId() != null) {
            Tag tag = tagRepository.findById(request.tagId())
                    .orElseThrow(() -> new EntityNotFoundException("해당 태그가 존재하지 않습니다."));

            TaskTag taskTag = TaskTag.create(task, tag);
            taskTagRepository.save(taskTag);
        }

        return ResponseDto.setSuccess("SUCCESS", TaskCreateResponseDto.from(task));

    }

    @Override
    public ResponseDto<List<TaskSearchResponseDto>> getAllTasks(Long projectId) {
        List<Task> tasks = taskRepository.findAllTaskById(projectId);
        List<TaskSearchResponseDto> result = tasks.stream()
                .map(TaskSearchResponseDto::from)
                .toList();


        return ResponseDto.setSuccess("SUCCESS", result);
    }

    @Override
    public ResponseDto<TaskDetailResponseDto> getTaskById(Long projectId, Long taskId
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        Task task = taskRepository.findById(taskId)
                .filter(t -> t.getProject().getId().equals(project.getId()))
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트 내에 존재하지 않는 직무입니다."));

        return ResponseDto.setSuccess("SUCCESS", TaskDetailResponseDto.from(task));
    }

    @Override
    public ResponseDto<List<TaskDetailResponseDto>> getCreatedUser(Long projectId, Long createdUser
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        User user = userRepository.findById(createdUser)
                .orElseThrow(() -> new IllegalArgumentException("해당 작성자가 존재하지 않습니다."));
        return null;
    }

    @Override
    public ResponseDto<List<TaskDetailResponseDto>> searchTasks(
            Long projectId, Long createUserId, TaskStatus status, PriorityStatus priority, LocalDate from, LocalDate to
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        LocalDate fromDate = (from != null) ? from : null;
        LocalDate toDate = (to != null) ? to : null;

        List<Task> tasks = taskRepository.searchTasks(projectId, createUserId, status, priority, from, to);

        List<TaskDetailResponseDto> dto = tasks.stream()
                .map(TaskDetailResponseDto::from)
                .toList();
        return ResponseDto.setSuccess("SUCCESS", dto);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN') or @authz.isSelf(#request.createdUserId, authentication)")
    public ResponseDto<TaskDetailResponseDto> updateTask(Long projectId, Long taskId, UserPrincipal principal, TaskUpdateRequestDto request
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 직무가 존재하지 않습니다."));

        if (!task.getCreatedUser().getId().equals(request.createdUserId())) {
            throw new SecurityException("작성자만 수정할 수 있습니다.");
        }

        task.update(
                request.title(),
                request.content(),
                parseTaskStatus(request.status()),
                parsePriorityStatus(request.priority()),
                request.dueDate()
        );
        return ResponseDto.setSuccess("SUCCESS", TaskDetailResponseDto.from(task));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN') or @authz.isSelf(#request.createdUserId, authentication)")
    public ResponseDto<Void> deleteTask(Long projectId, Long taskId, UserPrincipal principal
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 직무가 존재하지 않습니다."));
        taskRepository.delete(task);

        return ResponseDto.setSuccess("SUCCESS",null);
    }
}



package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.task.request.TaskCreateRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateRequestDto;
import org.example.o_lim.dto.task.request.TaskUpdateStatusRequestDto;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final TaskTagRepository taskTagRepository;

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
            throw new IllegalArgumentException("해당 프로젝트 내 직무가 중복됩니다.");
        }

        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Task task = Task.create(
                project,
                request.title(),
                request.content(),
                user,
                request.status(),
                request.priority(),
                request.dueDate()
        );

        List<Long> assigneeIds = request.assigneeIds() != null ? request.assigneeIds() : Collections.emptyList();
        List<User> assignees = userRepository.findAllById(assigneeIds);

        for (User assignee : assignees) {
            task.addAssignee(assignee);
        }

        taskRepository.save(task);
        taskTagRepository.flush();

        if (request.tagIds() != null && !request.tagIds().isEmpty()) {
            List<Tag> tags = tagRepository.findAllById(request.tagIds());

            for (Tag tag : tags) {
                if (!tag.getProject().getId().equals(project.getId())) {
                    throw new IllegalArgumentException("해당 프로젝트의 태그가 존재하지 않습니다." + tag.getId());
                }

                TaskTag taskTag = TaskTag.create(task, tag);
                taskTagRepository.save(taskTag);
                task.addTaskTag(taskTag);
            }
            taskTagRepository.flush();
        }

        Task saveTask = taskRepository.findByIdWithTaskTags(task.getId())
                .orElseThrow(() -> new IllegalArgumentException("저장된 직무를 찾을 수 없습니다."));

        return ResponseDto.setSuccess("SUCCESS", TaskCreateResponseDto.from(saveTask));

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
    public ResponseDto<List<TaskDetailResponseDto>> searchTasks(
            Long projectId, Long createUserId, TaskStatus status, PriorityStatus priority, LocalDateTime from, LocalDateTime to, LocalDate dueDate
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        LocalDateTime fromDate = (from != null) ? from : null;
        LocalDateTime toDate = (to != null) ? to : null;

        List<Task> tasks = taskRepository.searchTasks(projectId, createUserId, status, priority, from, to, dueDate);

        List<TaskDetailResponseDto> dto = tasks.stream()
                .map(TaskDetailResponseDto::from)
                .toList();

        return ResponseDto.setSuccess("SUCCESS", dto);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseDto<TaskDetailResponseDto> updateTask(Long projectId, Long taskId, UserPrincipal principal, TaskUpdateRequestDto request
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 직무가 존재하지 않습니다."));

        if (request.title() == null && request.content() == null && request.assigneeIds() == null
            && request.status() == null && request.priority() == null && request.dueDate() == null) {
                throw new IllegalArgumentException("수정할 정보를 입력해주세요.");
        }

        String newTitle = (request.title() != null && !request.title().isBlank()) ? request.title() : null;
        String newContent = (request.content() != null && !request.content().isBlank()) ? request.content() : null;
        List<Long> newAssignee = (request.assigneeIds() != null && !request.assigneeIds().isEmpty()) ? request.assigneeIds() : null;

        TaskStatus newStatus = task.getStatus();
        String statusStr = request.status();
        if (statusStr == null || statusStr.isBlank()) {

        } else {
            try {
                newStatus = TaskStatus.valueOf(statusStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("잘못된 status 값입니다.");
            }
        }

        PriorityStatus newPriority = task.getPriority();
        String priorityStr = request.priority();
        if (priorityStr == null || priorityStr.isBlank()) {

        } else {
            try {
                newPriority = PriorityStatus.valueOf(priorityStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("잘못된 priority 값입니다.");
            }
        }

        List<Long> newTagId = (request.tagId() != null && !request.tagId().isEmpty()) ? request.tagId() : null;

        String dueDateStr = request.dueDate();
        LocalDate newDueDate = null;
        boolean changedDueDate = false;
        if (dueDateStr == null) {
            if (task.getDueDate() != null) {
                newDueDate = null;
                changedDueDate = true;
            }
        } else if (dueDateStr.isBlank()) {
            newDueDate = task.getDueDate();
            changedDueDate = false;
        } else {
            try {
                LocalDate parsedDate = LocalDate.parse(dueDateStr);
                if (!parsedDate.equals(task.getDueDate())) {
                    newDueDate = parsedDate;
                    changedDueDate = true;
                } else {
                    newDueDate = task.getDueDate();
                    changedDueDate = false;
                }
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("dueDate 형식이 올바르지 않습니다.");
            }
        }

        boolean changedTitle = newTitle != null && !Objects.equals(task.getTitle(), newTitle);
        boolean changedContent = newContent != null && !Objects.equals(task.getContent(), newContent);
        boolean changedAssignee = newAssignee != null;
        boolean changedStatus = !task.getStatus().equals(newStatus);
        boolean changedPriority = !task.getPriority().equals(newPriority);
        boolean changedTagId = newTagId != null;

        if (!changedTitle && !changedContent && !changedAssignee && !changedStatus
             && !changedPriority && !changedTagId && !changedDueDate) {
            throw new IllegalArgumentException("변경된 개인정보가 없습니다.");
        }

        if (changedTitle) task.setTitle(newTitle);
        if (changedContent) task.setContent(newContent);
        if (changedAssignee) task.setAssignee(newAssignee, userRepository);
        if (changedStatus) task.setStatus(newStatus);
        if (changedPriority) task.setPriority(newPriority);
        if (changedTagId) task.setTagId(newTagId, tagRepository);
        if (changedDueDate) task.setDueDate(newDueDate);

        Task updatedTask = taskRepository.findByIdWithAssignees(taskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 직무의 담당자를 찾을 수 없습니다."));

        return ResponseDto.setSuccess("SUCCESS", TaskDetailResponseDto.from(updatedTask));
    }

    @Override
    @Transactional
    @PreAuthorize("@authz.isChange(#taskId, authentication)")
    public ResponseDto<TaskDetailResponseDto> updateTaskStatus(
            Long projectId, Long taskId, UserPrincipal principal, TaskUpdateStatusRequestDto request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        Task task = taskRepository.findByIdWithAssignees(taskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 직무가 존재하지 않습니다."));

        task.updateStatus(request.status());

        return ResponseDto.setSuccess("SUCCESS", TaskDetailResponseDto.from(task));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
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



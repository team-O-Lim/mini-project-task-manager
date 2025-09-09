package org.example.o_lim.dto.task.request;

import java.time.LocalDateTime;

public class TaskRequest {

    public record TaskCreateRequestDto(
            Long projectId,
            String title,
            String content,
            Long createdUserId,
            Long assignedUserId,
            String status,
            String priority,
            LocalDateTime dueDate
    ) {}





}

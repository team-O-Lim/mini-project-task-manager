package org.example.o_lim.dto.task.request;

import java.time.LocalDateTime;

public record TaskUpdateRequestDto(
        String title,
        String content,
        String status,
        String priority,
        LocalDateTime dueDate
){}
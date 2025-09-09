package org.example.o_lim.dto.task.request;

public record TaskUpdateRequestDto(
        String title,
        String content,
        String status,
        String priority
){}
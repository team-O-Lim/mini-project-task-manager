package org.example.o_lim.dto.task.request;

import jakarta.validation.constraints.NotBlank;
import org.example.o_lim.common.enums.TaskStatus;

public record TaskUpdateStatusRequestDto(
        @NotBlank(message = "직무상황의 기본은 TODO 입니다.")
        TaskStatus status,
        Long taskId
) {}

package org.example.o_lim.dto.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskSearchRequestDto(
        @NotNull(message = "projectId는 필수입니다.")
        Long projectId,

        @NotBlank(message = "직무명은 필수입니다.")
        String title
){}

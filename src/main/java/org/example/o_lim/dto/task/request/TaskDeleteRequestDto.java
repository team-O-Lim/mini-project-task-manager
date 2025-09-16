package org.example.o_lim.dto.task.request;

import jakarta.validation.constraints.NotNull;

public record TaskDeleteRequestDto(
        @NotNull(message = "직무 ID는 필수입니다.")
        Long id

) {}

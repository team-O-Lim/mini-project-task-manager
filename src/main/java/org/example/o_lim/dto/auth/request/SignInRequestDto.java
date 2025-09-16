package org.example.o_lim.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInRequestDto(
        @NotBlank @Size(min = 6, max = 14)
        String longinId,

        @NotBlank @Size(min = 8, max = 16)
        String password
) {
}

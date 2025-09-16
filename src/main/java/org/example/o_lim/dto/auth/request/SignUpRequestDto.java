package org.example.o_lim.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.o_lim.common.enums.Gender;

public record SignUpRequestDto(
        @NotBlank @Size(max = 50)
        String name,
        @NotBlank @Size(min = 6, max = 14)
        String longinId,
        @NotBlank @Size(min = 8, max = 16)
        String password,
        @NotBlank @Email @Size(max = 255)
        String email,
        @NotBlank @Size(max = 50)
        String nickname,

        Gender gender
) {
}

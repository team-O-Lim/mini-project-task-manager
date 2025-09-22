package org.example.o_lim.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserProfileUpdateRequest(
        @Size(max = 50)
        String nickname,

        @Email @Size(max = 255)
        String email
) {
}

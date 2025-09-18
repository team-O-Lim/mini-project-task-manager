package org.example.o_lim.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserProfileUpdateRequest(
        @NotBlank(message = "닉네임을 입력해주세요.") @Size(max = 50)
        String nickname,

        @NotBlank(message = "이메일을 입력해주세요.") @Email @Size(max = 255)
        String email
) {
}

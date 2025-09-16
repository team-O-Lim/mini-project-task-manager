package org.example.o_lim.dto.auth.response;

public record SignInResponseDto(
        String loginId,
        String name,
        String nickname,
        String gender
) {
}

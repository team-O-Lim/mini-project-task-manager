package org.example.o_lim.dto.user.response;

public record UserProfileResponseDto(
        String loginId,
        String name,
        String email,
        String nickname,
        String gender
) {
}

package org.example.o_lim.dto.auth.response;

import java.util.Set;

public record SignUpResponseDto(
        String loginId,
        String name,
        String nickname,
        String gender
) {
}

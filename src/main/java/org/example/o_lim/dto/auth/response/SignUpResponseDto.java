package org.example.o_lim.dto.auth.response;

import org.example.o_lim.common.enums.Gender;

import java.util.Set;

public record SignUpResponseDto(
        String loginId,
        String name,
        String nickname,
        Gender gender
) {
}

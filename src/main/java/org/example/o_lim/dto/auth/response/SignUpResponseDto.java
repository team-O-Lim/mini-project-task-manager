package org.example.o_lim.dto.auth.response;

import java.util.Set;

public record SignUpResponseDto(
        String tokenType,
        String accessToken,
        long expiresAt,
        String username,
        Set<String> roles
) {
}

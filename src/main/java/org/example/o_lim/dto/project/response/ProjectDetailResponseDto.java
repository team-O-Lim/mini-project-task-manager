package org.example.o_lim.dto.project.response;

import org.example.o_lim.entity.Project;

import java.time.LocalDateTime;

public record ProjectDetailResponseDto (
        Long id,
        Long adminId,
        String adminManagerNickname,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    // 단건 조회
    public static ProjectDetailResponseDto from(Project project) {
        return new ProjectDetailResponseDto(
                project.getId(),
                project.getAdmin().getId(),
                project.getAdmin().getNickname(),
                project.getTitle(),
                project.getDescription(),
                project.getCreatedAt(),
                project.getUpdatedAt()
            );
        }
    }

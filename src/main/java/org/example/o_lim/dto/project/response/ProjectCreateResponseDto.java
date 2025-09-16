package org.example.o_lim.dto.project.response;

import org.example.o_lim.entity.Project;

import java.time.LocalDateTime;

public record ProjectCreateResponseDto(
        Long id,
        Long adminId,
        String title,
        String description,
        String adminManagerNickname,
        LocalDateTime createdAt
) {
    public static ProjectCreateResponseDto fromEntity(Project project) {
        return new ProjectCreateResponseDto(
                project.getId(),
                project.getAdmin().getId(),
                project.getTitle(),
                project.getDescription(),
                project.getAdmin().getNickname(),
                project.getCreatedAt()
        );
    }
}

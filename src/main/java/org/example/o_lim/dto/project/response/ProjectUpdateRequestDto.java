package org.example.o_lim.dto.project.response;

import org.example.o_lim.entity.Project;
import java.time.LocalDateTime;

public record ProjectUpdateRequestDto (
        Long id,
        Long adminId,
        String title,
        String description,
        String adminManagerNickname,
        LocalDateTime updatedAt
) {
    public static ProjectUpdateRequestDto from(Project project) {
        return new ProjectUpdateRequestDto(
                project.getId(),
                project.getAdmin().getId(),
                project.getTitle(),
                project.getDescription(),
                project.getAdmin().getNickname(),
                project.getUpdatedAt()
        );
    }
}
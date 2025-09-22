package org.example.o_lim.dto.notification.response;

import org.example.o_lim.entity.Notification;

import java.time.LocalDateTime;

public record NotificationDetailResponseDto (
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static NotificationDetailResponseDto from (Notification notification) {
        return new NotificationDetailResponseDto(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getCreatedAt(),
                notification.getUpdatedAt()
        );
    }
}
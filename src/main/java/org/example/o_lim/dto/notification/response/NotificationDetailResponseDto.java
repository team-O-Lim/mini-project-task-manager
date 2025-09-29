package org.example.o_lim.dto.notification.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.o_lim.entity.Notification;
import java.time.LocalDateTime;

public record NotificationDetailResponseDto (
        Long id,
        String title,
        String content,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
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
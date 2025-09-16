package org.example.o_lim.dto.Notification.response;

import org.example.o_lim.entity.Notification;
import java.time.LocalDateTime;

public record NotificationDetailResponseDto (
     Long id,
     String title,
     String content,
     String nickname,
     LocalDateTime createdAt,
     LocalDateTime updatedAt
 ) {
    public static NotificationDetailResponseDto from (Notification notification) {
      return new NotificationDetailResponseDto(
              notification.getId(),
              notification.getTitle(),
              notification.getContent(),
              notification.getUser().getNickname(),
              notification.getCreatedAt(),
              notification.getUpdatedAt()
      );
    }
}

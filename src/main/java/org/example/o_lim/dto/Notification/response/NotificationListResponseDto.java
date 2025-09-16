package org.example.o_lim.dto.Notification.response;

import org.example.o_lim.entity.Notification;
import java.time.LocalDateTime;

public record NotificationListResponseDto (
     Long id,
     String title,
     Long project_id,
     LocalDateTime createdAt
 ) {
    public static NotificationListResponseDto from(Notification notification) {
      return new NotificationListResponseDto(
              notification.getId(),
              notification.getTitle(),
              notification.getProject().getId(),
              notification.getCreatedAt()
      );
    }
}

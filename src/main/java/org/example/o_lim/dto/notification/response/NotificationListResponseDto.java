package org.example.o_lim.dto.notification.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.o_lim.entity.Notification;
import java.time.LocalDateTime;

public record NotificationListResponseDto (
     Long id,
     Long project_id,
     String title,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
     LocalDateTime createdAt
 ) {
    public static NotificationListResponseDto from(Notification notification) {
      return new NotificationListResponseDto(
              notification.getId(),
              notification.getProject().getId(),
              notification.getTitle(),
              notification.getCreatedAt()
      );
    }
}

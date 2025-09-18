package org.example.o_lim.dto.aotification.response;

import org.example.o_lim.entity.Notification;
import org.example.o_lim.entity.User;

import java.time.LocalDateTime;

public record NotificationDetailResponseDto (
     Long id,
     String title,
     String content,
     String nickname,
     LocalDateTime createdAt,
     LocalDateTime updatedAt
 ) {
    public static NotificationDetailResponseDto from (Notification notification, User user) {
      return new NotificationDetailResponseDto(
              notification.getId(),
              notification.getTitle(),
              notification.getContent(),
              user.getNickname(),
              notification.getCreatedAt(),
              notification.getUpdatedAt()
      );
    }
}

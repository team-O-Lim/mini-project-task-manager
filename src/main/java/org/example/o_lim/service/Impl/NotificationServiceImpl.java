package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.notification.request.NotificationCreatedRequestDto;
import org.example.o_lim.dto.notification.request.NotificationUpdatedRequestDto;
import org.example.o_lim.dto.notification.response.NotificationDetailResponseDto;
import org.example.o_lim.dto.notification.response.NotificationListResponseDto;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.entity.Notification;
import org.example.o_lim.repository.NotificationRepository;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본 조회
 public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<NotificationDetailResponseDto> createNotification(UserPrincipal principal, NotificationCreatedRequestDto request) {
        validateTitleAndContent(request.title(), request.content());

        final String loginId = principal.getUsername();
        Notification Nickname = NotificationRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalAccessException("NICKNAME_NOT_FOUND"));

        Notification saved = NotificationRepository.save(Notification.create(request.title(), request.content(), nickname));

        NotificationDetailResponseDto data = NotificationDetailResponseDto.from(saved);
        return ResponseDto.setSuccess("SUCCESS", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN') or @authz.isNotificatonNickname(#notificationId, authentication)")
    public ResponseDto<NotificationDetailResponseDto> updateNotification(UserPrincipal principal, Long notificationId, NotificationUpdatedRequestDto request) {
       validateTitleAndContent(request.title(), request.content());

       if (notificationId == null) throw new IllegalAccessException("NOTIFICATION_ID_REQUIRED");

       Notification notification = NotificationRepository.findById(notificationId)
               .orElseThrow(() -> new IllegalArgumentException("NOTIFICATION_NOT_FOUND"));

       notification.update(request.title(), request.content());

       notificationRepository.flush();

       NotificationDetailResponseDto data = NotificationDetailResponseDto.from(notification);
        return ResponseDto.setSuccess("SUCCESS", data);
    }

    @Override
    public ResponseDto<List<NotificationListResponseDto>> getAllNotifications() {
     List<NotificationListResponseDto> data = null;

     if (id == null) throw new IllegalAccessException("NOTIFICATION_ID_REQUIRED");

     Notification notification = notificationRepository.findById(id)
             .orElseThrow(() -> new IllegalArgumentException("NOTIFICATION_NOT_FOUND"));

     data = NotificationDetailResponseDto.from(notification)

    return ResponseDto.setSuccess("SUCCESS", data);
    }

    @Override
    public ResponseDto<NotificationDetailResponseDto> getNotificationById(Long notificationId) {
        NotificationDetailResponseDto 
        return null;
    }

    @Override
    public ResponseDto<Void> deleteArticle(UserPrincipal principal, Long notificationId) {
        return null;
    }
}

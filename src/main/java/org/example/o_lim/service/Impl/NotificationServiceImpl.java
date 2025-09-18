package org.example.o_lim.service.Impl;


import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.notification.request.NotificationCreatedRequestDto;
import org.example.o_lim.dto.notification.request.NotificationUpdatedRequestDto;
import org.example.o_lim.dto.notification.response.NotificationDetailResponseDto;
import org.example.o_lim.dto.notification.response.NotificationListResponseDto;
import org.example.o_lim.dto.ResponseDto;
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
    public ResponseDto<NotificationDetailResponseDto> createNotification(UserPrincipal principal, NotificationCreatedRequestDto requestDto) {

        return null;
    }

    @Override
    public ResponseDto<NotificationDetailResponseDto> updateNotification(UserPrincipal principal, Long notificationId, NotificationUpdatedRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseDto<List<NotificationListResponseDto>> getAllNotifications() {
        return null;
    }

    @Override
    public ResponseDto<NotificationDetailResponseDto> getNotificationById(Long notificationId) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteArticle(UserPrincipal principal, Long notificationId) {
        return null;
    }
}

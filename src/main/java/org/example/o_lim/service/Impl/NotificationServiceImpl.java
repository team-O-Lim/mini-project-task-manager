package org.example.o_lim.service.Impl;

import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.notification.request.NotificationCreatedRequestDto;
import org.example.o_lim.dto.notification.request.NotificationUpdatedRequestDto;
import org.example.o_lim.dto.notification.response.NotificationDetailResponseDto;
import org.example.o_lim.dto.notification.response.NotificationListResponseDto;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 @RequiredArgsConstructor
 public class NotificationServiceImpl implements NotificationService {
    @Override
    public ResponseDto<NotificationDetailResponseDto> updateNotification(UserPrincipal principal, Long id, NotificationUpdatedRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseDto<NotificationDetailResponseDto> createNotification(UserPrincipal principal, NotificationCreatedRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseDto<List<NotificationListResponseDto>> getAllNotifications() {
        return null;
    }

    @Override
    public ResponseDto<NotificationDetailResponseDto> getNotificationById(Long id) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteArticle(UserPrincipal principal, Long id) {
        return null;
    }
}

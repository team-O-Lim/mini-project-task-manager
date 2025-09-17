package org.example.o_lim.service;


import com.sun.security.auth.UserPrincipal;
import jakarta.validation.Valid;
import org.example.o_lim.dto.notification.request.NotificationCreatedRequestDto;
import org.example.o_lim.dto.notification.request.NotificationUpdatedRequestDto;
import org.example.o_lim.dto.notification.response.NotificationDetailResponseDto;
import org.example.o_lim.dto.notification.response.NotificationListResponseDto;
import org.example.o_lim.dto.ResponseDto;

import java.util.List;

public interface NotificationService {


    ResponseDto<NotificationDetailResponseDto> updateNotification(UserPrincipal principal, Long id, @Valid NotificationUpdatedRequestDto requestDto);

    ResponseDto<NotificationDetailResponseDto> createNotification(UserPrincipal principal, @Valid NotificationCreatedRequestDto requestDto);

    ResponseDto<List<NotificationListResponseDto>> getAllNotifications();

    ResponseDto<NotificationDetailResponseDto> getNotificationById(Long id);

    ResponseDto<Void> deleteArticle(UserPrincipal principal, Long id);

}

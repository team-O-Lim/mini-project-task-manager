package org.example.o_lim.service;


import jakarta.validation.Valid;
import org.example.o_lim.dto.aotification.request.NotificationCreatedRequestDto;
import org.example.o_lim.dto.aotification.request.NotificationUpdatedRequestDto;
import org.example.o_lim.dto.aotification.response.NotificationDetailResponseDto;
import org.example.o_lim.dto.aotification.response.NotificationListResponseDto;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.security.UserPrincipal;
import java.util.List;

public interface NotificationService {

    ResponseDto<NotificationDetailResponseDto> createNotification(UserPrincipal principal, @Valid NotificationCreatedRequestDto requestDto);

    ResponseDto<NotificationDetailResponseDto> updateNotification(UserPrincipal principal, Long notificationId, @Valid NotificationUpdatedRequestDto requestDto);

    ResponseDto<List<NotificationListResponseDto>> getAllNotifications();

    ResponseDto<NotificationDetailResponseDto> getNotificationById(Long notificationId);

    ResponseDto<Void> deleteArticle(UserPrincipal principal, Long notificationId);

}

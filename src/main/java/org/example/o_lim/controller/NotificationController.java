package org.example.o_lim.controller;


import com.sun.security.auth.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.notification.request.NotificationCreatedRequestDto;
import org.example.o_lim.dto.notification.request.NotificationUpdatedRequestDto;
import org.example.o_lim.dto.notification.response.NotificationDetailResponseDto;
import org.example.o_lim.dto.notification.response.NotificationListResponseDto;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;


 @RestController
 @RequiredArgsConstructor
 //"/api/v1/notices"
 @RequestMapping(ApiMappingPattern.Notification.ROOT)

 public class NotificationController {

 private final NotificationService notificationService;

  //   생성 "/api/v1/notices"
    @PostMapping
    public ResponseEntity<ResponseDto<NotificationDetailResponseDto>> createNotification(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody NotificationCreatedRequestDto requestDto
            ) {
        ResponseDto<NotificationDetailResponseDto> responseDto = notificationService.createNotification(principal, requestDto);
       return ResponseEntity.ok().body(responseDto);
    }

    // 전체 조회 "/api/v1/notices"
    // @GetMapping
    @GetMapping
    public ResponseEntity<ResponseDto<List<NotificationListResponseDto>>> getAllNotifications() {
        ResponseDto<List<NotificationListResponseDto>> responseDto = notificationService.getAllNotifications();
        return ResponseEntity.ok().body(responseDto);
    }

   // 단건 조회 "/api/v1/notices/{noticeId}"
    @GetMapping(ApiMappingPattern.Notification.BY_ID)
    public ResponseEntity<ResponseDto<NotificationDetailResponseDto>> getNotificationById(
           @PathVariable Long id
    ) {
        ResponseDto<NotificationDetailResponseDto> response = notificationService.getNotificationById(id);
        return ResponseEntity.ok().body(response);
    }

    // 수정 "/api/v1/notices/{noticeId}"
    @PutMapping(ApiMappingPattern.Notification.BY_ID)
    public ResponseEntity<ResponseDto<NotificationDetailResponseDto>> updateNotification(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long id,
            @Valid @RequestBody NotificationUpdatedRequestDto requestDto
            ) {
           ResponseDto<NotificationDetailResponseDto> responseDto = notificationService.updateNotification(principal, id, requestDto);
           return ResponseEntity.ok().body(responseDto);
    }

    // 삭제 "/api/v1/notices/{noticeId}"
    @DeleteMapping(ApiMappingPattern.Notification.BY_ID)
    public ResponseEntity<ResponseDto<Void>> deleteNotification(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long id
    ) {
        ResponseDto<Void> response = notificationService.deleteArticle(principal, id);
        return ResponseEntity.ok().body(response);
    }

}

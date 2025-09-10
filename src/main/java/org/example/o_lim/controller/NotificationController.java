package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1/notices"
@RequestMapping(ApiMappingPattern.Notification.ROOT)
public class NotificationController {

    // 생성 "/api/v1/notices"
//    @PostMapping

    // 전체 조회 "/api/v1/notices"
//    @GetMapping

    // 단건 조회 "/api/v1/notices/{noticeId}"
//    @GetMapping(ApiMappingPattern.Notification.BY_ID)

    // 수정 "/api/v1/notices/{noticeId}"
//    @PutMapping(ApiMappingPattern.Notification.BY_ID)

    // 삭제 "/api/v1/notices/{noticeId}"
//    @DeleteMapping(ApiMappingPattern.Notification.BY_ID)


}
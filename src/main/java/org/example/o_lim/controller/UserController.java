package org.example.o_lim.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.user.request.UserProfileUpdateRequest;
import org.example.o_lim.dto.user.response.UserProfileResponseDto;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.Users.ROOT)
public class UserController {
    private final UserService userService;

//    마이페이지
    @GetMapping(ApiMappingPattern.Users.MY_INFO)
    public ResponseEntity<ResponseDto<UserProfileResponseDto>> getMyInfo(
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        ResponseDto<UserProfileResponseDto> response = userService.getMyInfo(userPrincipal);

        return ResponseEntity.ok().body(response);
    }
//    회원정보수정
    @PutMapping(ApiMappingPattern.Users.MY_INFO)
    public ResponseEntity<ResponseDto<UserProfileResponseDto>> updateMyInfo(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UserProfileUpdateRequest request
    ) {
        ResponseDto<UserProfileResponseDto> response = userService.updateMyInfo(userPrincipal, request);

        return ResponseEntity.ok().body(response);
    }

}

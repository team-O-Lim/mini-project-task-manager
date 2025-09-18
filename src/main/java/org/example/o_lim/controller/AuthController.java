package org.example.o_lim.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.auth.request.FindIdRequestDto;
import org.example.o_lim.dto.auth.request.PasswordChangeRequestDto;
import org.example.o_lim.dto.auth.request.SignInRequestDto;
import org.example.o_lim.dto.auth.request.SignUpRequestDto;
import org.example.o_lim.dto.auth.response.FindIdResponseDto;
import org.example.o_lim.dto.auth.response.PasswordChangeResponseDto;
import org.example.o_lim.dto.auth.response.SignInResponseDto;
import org.example.o_lim.dto.auth.response.SignUpResponseDto;
import org.example.o_lim.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.Auth.ROOT)
public class AuthController {
    private final AuthService authService;

//    회원가입
    @PostMapping(ApiMappingPattern.Auth.SIGN_UP)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(
            @Valid @RequestBody SignUpRequestDto request) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(request);

        return ResponseEntity.ok().body(response);
    }

//    로그인
    @PostMapping(ApiMappingPattern.Auth.SIGN_IN)
    public ResponseEntity<ResponseDto<SignInResponseDto>> signIn(
            @Valid @RequestBody SignInRequestDto request) {
        ResponseDto<SignInResponseDto> response = authService.signIn(request);

        return ResponseEntity.ok().body(response);
    }
//    아이디 찾기
    @PostMapping(ApiMappingPattern.Auth.FIND_ID)
    public ResponseEntity<ResponseDto<FindIdResponseDto>> findId(
            @Valid FindIdRequestDto request) {
        ResponseDto<FindIdResponseDto> response = authService.findId(request);

        return ResponseEntity.ok().body(response);
    }

//    비밀번호 찾기
    @PostMapping(ApiMappingPattern.Auth.RESET_PASSWORD)
    public ResponseEntity<ResponseDto<PasswordChangeResponseDto>> changePassword(
            @Valid PasswordChangeRequestDto request) {
        ResponseDto<PasswordChangeResponseDto> response = authService.changePassword(request);

        return ResponseEntity.ok().body(response);
    }
}

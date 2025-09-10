package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "api/v1"
@RequestMapping(ApiMappingPattern.BASE)
public class UserController {

    // 회원 가입 "/api/v1/signup"
//     @PostMapping(ApiMappingPattern.Auth.SIGN_UP)

    // 로그인 "/api/v1/login"
//     @PostMapping(ApiMappingPattern.Auth.LOG_IN)

    // 개인정보 수정 "/api/v1/users/me"
//     @PutMapping(ApiMappingPattern.Users.MY_INFO)

    // 프로필 조회 "/api/v1/users/me"
//     @GetMapping(ApiMappingPattern.Users.MY_INFO)

    // 아이디 찾기 "/api/v1//users/find-id"
//     @GetMapping(ApiMappingPattern.Auth.FIND_ID)

    // 비밀번호 재설정 "/api/v1/users/reset-pw"
//     @PutMapping(ApiMappingPattern.Auth.RESET_PASSWORD)

}

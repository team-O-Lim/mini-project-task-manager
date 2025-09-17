package org.example.o_lim.service.Impl;

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
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<SignInResponseDto> signIn(SignInRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<FindIdResponseDto> findId(FindIdRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<PasswordChangeResponseDto> changePassword(PasswordChangeRequestDto request) {
        return null;
    }
}

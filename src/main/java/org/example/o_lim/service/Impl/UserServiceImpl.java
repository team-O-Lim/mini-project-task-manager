package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.user.request.UserProfileUpdateRequest;
import org.example.o_lim.dto.user.response.UserProfileResponseDto;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public ResponseDto<UserProfileResponseDto> getMyInfo(UserPrincipal principal) {
        return null;
    }

    @Override
    public ResponseDto<UserProfileResponseDto> updateMyInfo(UserPrincipal principal, UserProfileUpdateRequest request) {
        return null;
    }
}

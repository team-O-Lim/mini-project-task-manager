package org.example.o_lim.service;

import jakarta.validation.Valid;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.user.request.UserProfileUpdateRequest;
import org.example.o_lim.dto.user.response.UserProfileResponseDto;
import org.example.o_lim.security.UserPrincipal;

public interface UserService {
    ResponseDto<UserProfileResponseDto> getMyInfo(UserPrincipal principal);

    ResponseDto<UserProfileResponseDto> updateMyInfo(UserPrincipal principal, @Valid UserProfileUpdateRequest request);
}

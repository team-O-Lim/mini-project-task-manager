package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.user.request.UserProfileUpdateRequest;
import org.example.o_lim.dto.user.response.UserProfileResponseDto;
import org.example.o_lim.entity.User;
import org.example.o_lim.repository.UserRepository;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.security.util.PrincipalUtils;
import org.example.o_lim.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<UserProfileResponseDto> getMyInfo(UserPrincipal principal) {
        PrincipalUtils.requiredActive(principal);

        User user = userRepository.findByLoginId(principal.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다."));

        UserProfileResponseDto response
                = new UserProfileResponseDto(
                        user.getLoginId(),
                        user.getName(),
                        user.getEmail(),
                        user.getNickname(),
                        user.getGender()
        );

        return ResponseDto.setSuccess("조회 성공", response);
    }

    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<UserProfileResponseDto> updateMyInfo(UserPrincipal principal, UserProfileUpdateRequest request) {
        PrincipalUtils.requiredActive(principal);

        User user = userRepository.findByLoginId(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다."));

        if(request.nickname() == null && request.email() == null) {
            throw new IllegalArgumentException("수정할 정보를 입력해주세요");
        }

        boolean changedNickname = !request.nickname().isBlank() && request.nickname() != null && !Objects.equals(user.getNickname(), request.nickname());
        boolean changedEmail = !request.email().isBlank() && request.email() != null && !Objects.equals(user.getEmail(), request.email());
        
        if(!changedNickname && !changedEmail) {
            throw new IllegalArgumentException("변경된 개인정보가 없습니다.");
        }

        if(changedNickname) user.setNickname(request.nickname());
        if(changedEmail) user.setEmail(request.email());

        UserProfileResponseDto response =
                new UserProfileResponseDto(
                        user.getLoginId(),
                        user.getName(),
                        user.getEmail(),
                        user.getNickname(),
                        user.getGender()
                );

        return ResponseDto.setSuccess("개인정보가 수정되었습니다.", response);
    }
}

package org.example.o_lim.service.Impl;

import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.admin.request.AddRoleRequestDto;
import org.example.o_lim.dto.admin.request.RemoveRoleRequestDto;
import org.example.o_lim.dto.admin.request.UpdateRoleRequestDto;
import org.example.o_lim.dto.admin.response.AddRoleResponseDto;
import org.example.o_lim.dto.admin.response.RemoveRoleResponseDto;
import org.example.o_lim.dto.admin.response.UpdateRoleResponseDto;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public ResponseDto<AddRoleResponseDto> addRoles(UserPrincipal principal, AddRoleRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<RemoveRoleResponseDto> removeRole(UserPrincipal principal, RemoveRoleRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<UpdateRoleResponseDto> replaceRoles(UserPrincipal principal, UpdateRoleRequestDto request) {
        return null;
    }
}

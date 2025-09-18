package org.example.o_lim.service;

import jakarta.validation.Valid;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.project.request.ProjectCreateRequestDto;
import org.example.o_lim.dto.project.response.ProjectDetailResponseDto;
import org.example.o_lim.dto.project.response.ProjectListResponseDto;
import org.example.o_lim.dto.project.response.ProjectTaskCountResponseDto;
import org.example.o_lim.dto.project.response.ProjectUpdateRequestDto;
import org.example.o_lim.security.UserPrincipal;

import java.util.List;

public interface ProjectService {
    ResponseDto<ProjectDetailResponseDto> createProject(UserPrincipal principal, @Valid ProjectCreateRequestDto dto);

    ResponseDto<ProjectDetailResponseDto> getProjectById(Long projectId);

    ResponseDto<List<ProjectListResponseDto>> getAllProjects();

    ResponseDto<ProjectDetailResponseDto> updateProject(UserPrincipal principal, Long projectId, @Valid ProjectUpdateRequestDto dto);

    ResponseDto<Void> deleteProject(UserPrincipal principal, Long projectId);

    ResponseDto<List<ProjectTaskCountResponseDto>> getTaskCountDesc();
}

package org.example.o_lim.service;

import jakarta.validation.Valid;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.project.request.ProjectCreateRequestDto;
import org.example.o_lim.dto.project.response.ProjectDetailResponseDto;

public interface ProjectService {
    ResponseDto<ProjectDetailResponseDto> createProject(@Valid ProjectCreateRequestDto dto);

    ResponseDto<ProjectDetailResponseDto> getProjectById(Long projectId);
}

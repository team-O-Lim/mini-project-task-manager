package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.project.request.ProjectCreateRequestDto;
import org.example.o_lim.dto.project.response.ProjectDetailResponseDto;
import org.example.o_lim.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    @Override
    public ResponseDto<ProjectDetailResponseDto> createProject(ProjectCreateRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<ProjectDetailResponseDto> getProjectById(Long projectId) {
        return null;
    }
}

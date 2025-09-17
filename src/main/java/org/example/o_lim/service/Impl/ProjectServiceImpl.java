package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.project.request.ProjectCreateRequestDto;
import org.example.o_lim.dto.project.response.ProjectDetailResponseDto;
import org.example.o_lim.dto.project.response.ProjectListResponseDto;
import org.example.o_lim.dto.project.response.ProjectTaskCountResponseDto;
import org.example.o_lim.dto.project.response.ProjectUpdateRequestDto;
import org.example.o_lim.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResponseDto<List<ProjectListResponseDto>> getAllProjects() {
        return null;
    }

    @Override
    public ResponseDto<ProjectDetailResponseDto> updateProject(Long projectId, ProjectUpdateRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteProject(Long projectId) {
        return null;
    }

    @Override
    public ResponseDto<List<ProjectTaskCountResponseDto>> getTaskCountDesc() {
        return null;
    }
}

package org.example.o_lim.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.tag.request.TagRequestDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.entity.Project;
import org.example.o_lim.entity.Tag;
import org.example.o_lim.repository.ProjectRepository;
import org.example.o_lim.repository.TagRepository;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.TagService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto<TagResponseDto> createTag(UserPrincipal principal, TagRequestDto request,Long projectId) {

        TagResponseDto data = null;

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ProjectId가 없습니다." + projectId));

        Tag tags = Tag.create(project, request.name(), request.color());
        Tag saved = tagRepository.save(tags);

        data = TagResponseDto.from(saved);

        return ResponseDto.setSuccess("태그가 등록되었습니다.", data);
    }

    @Override
    public ResponseDto<List<TagResponseDto>> getAllTag(Long projectId) {

        List<TagResponseDto> data = null;

        List<Tag> tags = tagRepository.findByProjectId(projectId);

        if (tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("현재 없는 project 입니다.");
        }

        data = tags.stream()
                .map(TagResponseDto::from)
                .toList();

        return ResponseDto.setSuccess("태그가 조회되었습니다.", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto<TagResponseDto> deleteTag(UserPrincipal principal, Long projectId, Long tagId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("해당 TagId 없습니다: " + tagId));

        if (!tag.getProject().getId().equals(projectId)) {
            throw new IllegalArgumentException("해당 projectId가 없습니다: " + projectId);
        }

        tagRepository.delete(tag);

        return ResponseDto.setSuccess("태그가 삭제되었습니다.", null);
    }
}
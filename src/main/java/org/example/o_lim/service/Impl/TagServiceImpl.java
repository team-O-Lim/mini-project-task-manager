package org.example.o_lim.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.tag.request.TagRequestDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.entity.Tag;
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

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto<TagResponseDto> createTag(UserPrincipal principal, TagRequestDto request,Long projectId) {

        Tag tag = tagRepository.findByProjectId(projectId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ProjectId가 없습니다." + projectId));

        Tag saved = tagRepository.save(tag);

        TagResponseDto data = null;

        data = TagResponseDto.from(saved);

        return ResponseDto.setSuccess("태그가 등록되었습니다.", data);
    }

    @Override
    public ResponseDto<List<TagResponseDto>> getAllTag(Long projectId) {

        Tag tag = tagRepository.findByProjectId(projectId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ProjectId가 없습니다." + projectId));

        List<TagResponseDto> data = null;

        data = tagRepository.findByProjectId(projectId).stream()
                .map(TagResponseDto::from)
                .toList();

        return ResponseDto.setSuccess("태그가 조회되었습니다.", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto<TagResponseDto> deleteTag(UserPrincipal principal, Long projectId, Long tagId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("해당 TagId 없습니다." + tagId));

        tag.delete(tag);

        return ResponseDto.setSuccess("태그가 삭제되었습니다.", null);
    }
}

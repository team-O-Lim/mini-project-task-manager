package org.example.o_lim.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.tag.request.TagRequestDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.repository.TagRepository;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;


    @Override
    public ResponseDto<TagResponseDto> createTag(UserPrincipal userPrincipal, TagRequestDto req) {
        return null;
    }

    @Override
    public ResponseDto<List<TagResponseDto>> getAllTag(Long projectid) {
        return null;
    }

    @Override
    public ResponseDto<TagResponseDto> deleteTag(UserPrincipal userPrincipal, Long projectId, Long tagId) {
        return null;
    }
}

package org.example.o_lim.service;

import jakarta.validation.constraints.Positive;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.tag.request.TagRequestDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.security.UserPrincipal;

import java.util.List;

public interface TagService {

    ResponseDto<TagResponseDto> createTag(UserPrincipal userPrincipal, TagRequestDto req);
    ResponseDto<List<TagResponseDto>> getAllTag(@Positive(message = "1이상의 정수여야 합니다.") Long projectid);
    ResponseDto<TagResponseDto> deleteTag(UserPrincipal userPrincipal, @Positive(message = "문자여야만 가능합니다.") Long projectId, @Positive(message = "문자여야만 가능합니다.") Long tagId);
}

package org.example.o_lim.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.tag.request.TagRequestDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.security.UserPrincipal;
import org.example.o_lim.service.TagService;
import org.hibernate.annotations.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Comment("기본 경로 값 =  /api/v1/projects/{projectId}/tags")
@RequestMapping(ApiMappingPattern.Tags.ROOT)
public class TagController {

    private final TagService tagService;

    @Comment("댓글생성 = ROOT")
    @PostMapping
    public ResponseEntity<ResponseDto<TagResponseDto>> createTag(
             @AuthenticationPrincipal UserPrincipal userPrincipal,
             @Valid @RequestBody TagRequestDto request,
             @PathVariable Long projectId
             ) {
        ResponseDto<TagResponseDto> response = tagService.createTag(userPrincipal, request, projectId);

        return ResponseEntity.ok().body(response);
    }

    @Comment("태그조회 = ROOT")
    @GetMapping
    public ResponseEntity<ResponseDto<List<TagResponseDto>>> getAllTag(
            @PathVariable Long projectId
            ) {
        ResponseDto<List<TagResponseDto>> response = tagService.getAllTag(projectId);

        return ResponseEntity.ok().body(response);
    }

    @Comment("태그삭제 =  ROOT + /{tagId}")
    @DeleteMapping(ApiMappingPattern.Tags.BY_ID)
    public ResponseEntity<ResponseDto<TagResponseDto>> deleteTag(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long projectId,
            @PathVariable Long tagId
            ) {
        ResponseDto<TagResponseDto> response = tagService.deleteTag(userPrincipal, projectId, tagId);

        return ResponseEntity.ok().body(response);
    }
}

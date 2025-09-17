package org.example.o_lim.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.example.o_lim.dto.ResponseDto;
import org.example.o_lim.dto.tag.request.TagRequestDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// "/api/v1/projects"
@RequestMapping(ApiMappingPattern.Tags.ROOT)
public class TagController {

    private final TagService tagService;

    // 생성 "/api/v1/projects/{projectId}/tags"
//     @PostMapping
//     public ResponseEntity<ResponseDto<TagResponseDto>> createTag(
//             @AuthenticationPrincipal UserPrincipal userPrincipal,
//             @RequestBody TagRequestDto req
//             ) {
//         ResponseDto<TagResponseDto> response = tagService.createTag(userPrincipal, req);
//
//         return ResponseEntity.ok().body(response);
//     }
//
//    // 삭제 "/api/v1/projects/{projectId}/tags/{tagId}"
//    @DeleteMapping(ApiMappingPattern.Tags.BY_ID)
//    public ResponseEntity<ResponseDto<TagResponseDto>> deleteTag(
//            @AuthenticationPrincipal UserPrincipal userPrincipal,
//            @PathVariable(name) @Positive(message = "문자여야만 가능합니다.") String name
//    ) {
//        ResponseDto<TagResponseDto> response = tagService.deleteTag(userPrincipal, name);
//
//        return ResponseEntity.ok().body(response);
//    }

}

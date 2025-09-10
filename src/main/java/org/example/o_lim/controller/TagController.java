package org.example.o_lim.controller;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
// "/api/v1/projects"
@RequestMapping(ApiMappingPattern.Tags.ROOT)
public class TagController {

    // 생성 "/api/v1/projects/{projectId}/tags"
//     @PostMapping

    // 삭제 "/api/v1/projects/{projectId}/tags/{tagId}"
//    @DeleteMapping(ApiMappingPattern.Tags.BY_ID)

}

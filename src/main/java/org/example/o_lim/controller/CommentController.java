package org.example.o_lim.controller;


import lombok.RequiredArgsConstructor;
import org.example.o_lim.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
// "/api/v1/tasks"
@RequestMapping(ApiMappingPattern.Projects.BY_ID)
public class CommentController {
}

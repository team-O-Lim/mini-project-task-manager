package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskSimpleResponseDto(
        String title,
        Long createUserId,
        String status,
        String priority,
        Long tagId
     ) {}

package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.entity.Task;
import org.example.o_lim.entity.TaskTag;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskSearchResponseDto(
        Long projectId,
        String title,
        Long createUserId,
        TaskStatus status,
        PriorityStatus priority,
        List<TagResponseDto> tags
){
    public static  TaskSearchResponseDto from(Task task) {
        if(task == null) return null;
        List<TagResponseDto> tagDtos = task.getTaskTags().stream()
                .filter(Objects::nonNull)
                .map(TaskTag::getTag)
                .filter(Objects::nonNull)
                .map(TagResponseDto::from)
                .toList();

        return new TaskSearchResponseDto(
                task.getProject().getId(),
                task.getTitle(),
                task.getCreatedUser().getId(),
                task.getStatus(),
                task.getPriority(),
                tagDtos

        );
    }
}


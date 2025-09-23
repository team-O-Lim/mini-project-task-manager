package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.entity.Task;
import org.example.o_lim.entity.TaskTag;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskCreateResponseDto(
        Long projectId,
        String title,
        Long createUserId,
        String content,
        List<TagResponseDto> tags,
        TaskStatus status,
        PriorityStatus priority,
        LocalDate dueDate
){
    public static  TaskCreateResponseDto from(Task task){
        if(task == null) return null;

        List<TagResponseDto> tagDtos = task.getTaskTags().stream()
                .filter(Objects::nonNull)
                .map(TaskTag::getTag)
                .filter(Objects::nonNull)
                .map(TagResponseDto::from)
                .toList();

        return new TaskCreateResponseDto(
                task.getProject().getId(),
                task.getTitle(),
                task.getCreatedUser().getId(),
                task.getContent(),
                tagDtos,
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );

    }
}

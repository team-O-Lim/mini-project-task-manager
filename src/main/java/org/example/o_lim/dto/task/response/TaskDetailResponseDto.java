package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.dto.comment.response.CommentDetailResponseDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
import org.example.o_lim.entity.Comment;
import org.example.o_lim.entity.Task;
import org.example.o_lim.entity.TaskTag;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskDetailResponseDto(
        Long id,
        Long projectId,
        String title,
        String content,
        Long createUserId,
        TaskStatus status,
        PriorityStatus priority,
        List<TagResponseDto> tags,
        LocalDate dueDate,
        List<CommentDetailResponseDto> comments
) {
    public static  TaskDetailResponseDto from(Task task){
        if(task == null) return null;

        List<TagResponseDto> tagDtos = task.getTaskTags().stream()
                .filter(Objects::nonNull)
                .map(TaskTag::getTag)
                .filter(Objects::nonNull)
                .map(TagResponseDto::from)
                .toList();

        List<Comment> comments
                = task.getComments() != null ? task.getComments() : Collections.emptyList();

        List<CommentDetailResponseDto> commentDtos = comments.stream()
                .filter(Objects::nonNull)
                .map(CommentDetailResponseDto::from)
                .toList();

        return new TaskDetailResponseDto(
                task.getId(),
                task.getProject().getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreatedUser().getId(),
                task.getStatus(),
                task.getPriority(),
                tagDtos,
                task.getDueDate(),
                commentDtos
        );
    }
}

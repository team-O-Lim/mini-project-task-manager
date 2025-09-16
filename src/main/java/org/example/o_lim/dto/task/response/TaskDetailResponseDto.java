package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.dto.comment.response.CommentDetailResponse;
import org.example.o_lim.dto.tag.response.TagResponse;
import org.example.o_lim.entity.Comment;
import org.example.o_lim.entity.Task;

import java.time.LocalDate;
import java.util.ArrayList;
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
        String status,
        String priority,
        List<TagResponse> tags,
        LocalDate dueDate,
        List<CommentDetailResponse> comments
) {
    public static  TaskDetailResponseDto from(Task task){
        if(task == null) return null;

        List<Comment> comments
                = task.getComments() != null ? task.getComments() : Collections.emptyList();

        List<CommentDetailResponse> commentDtos = comments.stream()
                .filter(Objects::nonNull)
                .map(Comment::from)
                .toList();
        return new TaskDetailResponseDto(
                task.getId(),
                task.getProject().getId(),
                task.getTitle(),
                task.getContent(),
                task.getCreatedUser().getId(),
                task.getStatus(),
                task.getPriority(),
                task.getTag(),
                commentDtos
        );
    }
}

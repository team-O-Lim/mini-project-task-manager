package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.dto.comment.response.CommentResponseDto;
import org.example.o_lim.dto.tag.response.TagResponseDto;
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
        List<TagResponseDto> tags,
        LocalDate dueDate,
        List<CommentResponseDto> comments
) {
//    public static  TaskDetailResponseDto from(Task task){
//        if(task == null) return null;
//
//        List<Comment> comments
//                = task.getComments() != null ? task.getComments() : Collections.emptyList();
//
//        List<CommentResponseDto> commentDtos = comments.stream()
//                .filter(Objects::nonNull)
//                .map(CommentResponseDto::from)
//                .toList();
//        return new TaskDetailResponseDto(
//                task.getId(),
//                task.getProject().getId(),
//                task.getTitle(),
//                task.getContent(),
//                task.getCreatedUser().getId(),
//                task.getStatus(),
//                task.getPriority(),
//                task.getTag(),
//                task.getDueDate(),
//                commentDtos
//        );
//    }
}

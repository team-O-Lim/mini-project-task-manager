package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
        @NotNull(message = "projectId는 필수입니다.")
        Long projectId,

        @NotBlank(message = "직무명은 필수입니다.")
        @Size(max = 200, message = "직무명은 최대 200자 까지입니다.")
        String title,

        @NotBlank(message = "직무 작성자아이디는 필수입니다.")
        Long createUserId,

        @NotBlank(message = "직무내용은 필수입니다.")
        String content,

        List<TagResponseDto> tags,

        @NotBlank(message = "직무상황의 기본은 TODO 입니다.")
        TaskStatus status,

        @NotBlank(message = "직무 우선사항의 기본은 MEDIUM 입니다.")
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

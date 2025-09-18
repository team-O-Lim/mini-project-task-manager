package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.Task;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskUpdateResponseDto(
        @NotBlank(message = "직무명은 필수입니다.")
        @Size(max = 200, message = "직무명은 최대 200자 까지입니다.")
        String title,

        @NotBlank(message = "직무내용은 필수입니다.")
        String content,

        @NotBlank(message = "직무상황의 기본은 TODO 입니다.")
        TaskStatus status,

        @NotBlank(message = "직무 우선사항의 기본은 MEDIUM 입니다.")
        PriorityStatus priority,

        @Comment("마감일은 필수는 아니지만 권장합니다.")
        LocalDate dueDate
){

    public static  TaskUpdateResponseDto from(Task task){
        if(task == null) return null;

        return new TaskUpdateResponseDto(
                task.getTitle(),
                task.getContent(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );
    }
}
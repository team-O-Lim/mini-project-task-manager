package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.Task;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskUpdateResponseDto(
        String title,
        String content,
        TaskStatus status,
        PriorityStatus priority,
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
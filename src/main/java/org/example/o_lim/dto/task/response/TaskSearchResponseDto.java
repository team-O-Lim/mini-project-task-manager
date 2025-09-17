package org.example.o_lim.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.o_lim.entity.Task;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskSearchResponseDto(
        Long projectId,
        String title
){
    public static  TaskSearchResponseDto from(Task task) {
        if(task == null) return null;
        return  new TaskSearchResponseDto(
                task.getProject().getId(),
                task.getTitle()
        );
    }

}


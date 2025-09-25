package org.example.o_lim.dto.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public record TaskUpdateRequestDto(
        @Size(max = 200, message = "직무명은 최대 200자 까지입니다.")
        String title,

        String content,

        List<Long> assigneeIds,

//        TaskStatus status,
        String status,

//        PriorityStatus priority,
        String priority,

        List<Long> tagId,

        String dueDate

){}
package org.example.o_lim.dto.task.request;

import jakarta.validation.constraints.Size;
import java.util.List;

public record TaskUpdateRequestDto(
        @Size(max = 200, message = "직무명은 최대 200자 까지입니다.")
        String title,

        String content,

        List<Long> assigneeIds,

        String status,

        String priority,

        List<Long> tagId,

        String dueDate
){}
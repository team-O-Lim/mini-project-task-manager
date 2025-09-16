package org.example.o_lim.dto.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

public record TaskCreateRequestDto(

        @NotNull(message = "projectId는 필수입니다.")
        Long projectId,

        @NotBlank(message = "직무명은 필수입니다.")
        @Size(max = 200, message = "직무명은 최대 200자 까지입니다.")
        String title,

        @NotBlank(message = "직무 작성자아이디는 필수입니다.")
        Long createUserId,

        @NotBlank(message = "직무내용은 필수입니다.")
        String content,

        Long tagId,

        @NotBlank(message = "직무상황의 기본은 TODO 입니다.")
        String status,

        @NotBlank(message = "직무 우선사항의 기본은 MEDIUM 입니다.")
        String priority,

        @Comment("마감일은 필수는 아니지만 권장합니다.")
        LocalDate dueDate
){}

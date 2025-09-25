package org.example.o_lim.dto.task.request;

import jakarta.annotation.Nullable;

import java.util.List;

public record TaskDeleteAssigneeAndTag(
        @Nullable
        List<Long> assigneeIds,

        @Nullable
        List<Long> tagId
) {}

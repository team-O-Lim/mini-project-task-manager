package org.example.o_lim.security.util;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.entity.TaskAssignees;
import org.example.o_lim.repository.TaskAssigneesRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequiredArgsConstructor
@Component("authz")
public class AuthorizationChecker {
    private final TaskAssigneesRepository taskAssigneesRepository;

    public boolean isChange(Long taskId, Authentication principal) throws AccessDeniedException {
        if (principal == null || taskId == null) return false;

        String loginId = principal.getName();

        List<TaskAssignees> results = taskAssigneesRepository.findByTaskId(taskId).stream()
                .filter(assignee -> assignee.getAssignees().getLoginId().equals(loginId))
                .toList();

        if(results.isEmpty()) {
            throw new AccessDeniedException("해당 Task의 담당자가 아닙니다.");
        }
        return true;
    }
}

package org.example.o_lim.security.util;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.entity.TaskAssignees;
import org.example.o_lim.repository.TaskAssigneesRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("authz")
public class AuthorizationChecker {
    private final TaskAssigneesRepository taskAssigneesRepository;

    public boolean isChange(Long taskId, Authentication principal) {
        if (principal == null || taskId == null) return false;

        String loginId = principal.getName();

        TaskAssignees taskAssignee = taskAssigneesRepository.findByTaskId(taskId)
                .orElse(null);
        if(taskAssignee == null) return false;

        return taskAssignee.getAssignees().getLoginId().equals(loginId);
    }
}

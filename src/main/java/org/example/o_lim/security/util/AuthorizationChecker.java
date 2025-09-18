package org.example.o_lim.security.util;

import lombok.RequiredArgsConstructor;
import org.example.o_lim.entity.TaskAssignees;
import org.example.o_lim.repository.TaskAssigneesRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component("authz")
public class AuthorizationChecker {
    private final TaskAssigneesRepository taskAssigneesRepository;

    public boolean isChange(Long taskId, Authentication principal) {
        if (principal == null || taskId == null) return false;

        String loginId = principal.getName();

//        List<Boolean> results = taskAssigneesRepository.findByTaskId(taskId).stream()
//                .map(assignee -> assignee.getAssignees().getLoginId().equals(loginId))
//                .toList();
//
//        return results.contains(true);
//
//        =============================================================================================
//
//        return taskAssigneesRepository.findByTaskId(taskId).stream()
//                .anyMatch(assignees -> assignees.getAssignees().getLoginId().equals(loginId));
//
//        =============================================================================================
//
//        List<String> assigneeId = taskAssigneesRepository.findByTaskId(taskId).stream()
//                .map(assignee -> assignee.getAssignees().getLoginId())
//                .toList();
//        return assigneeId.contains(loginId);
    }
}

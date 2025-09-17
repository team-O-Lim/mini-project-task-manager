package org.example.o_lim.repository;

import org.example.o_lim.entity.TaskAssignees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskAssigneesRepository extends JpaRepository<TaskAssignees, Long> {
    Optional<TaskAssignees> findByTaskId(Long taskId);
}

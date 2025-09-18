package org.example.o_lim.repository;

import org.example.o_lim.entity.TaskAssignees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssigneesRepository extends JpaRepository<TaskAssignees, Long> {
    List<TaskAssignees> findByTaskId(Long taskId);
}

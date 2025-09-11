package org.example.o_lim.repository;

import org.example.o_lim.entity.TaskAssignees;
import org.example.o_lim.entity.TaskAssigneesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssigneesRepository extends JpaRepository<TaskAssignees, TaskAssigneesId> {
}

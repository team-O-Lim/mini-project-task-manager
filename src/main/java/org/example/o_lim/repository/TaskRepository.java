package org.example.o_lim.repository;

import org.example.o_lim.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    boolean existsByProjectIdAndTitle(Long projectId, String title);

    opt<Task> findAllTaskByIdDesc();

    Long id(Long id);
}

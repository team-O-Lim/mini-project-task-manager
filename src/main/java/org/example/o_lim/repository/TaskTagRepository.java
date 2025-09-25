package org.example.o_lim.repository;

import org.example.o_lim.entity.Task;
import org.example.o_lim.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
    void deleteAllByTask(Task task);
}

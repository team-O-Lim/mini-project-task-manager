package org.example.o_lim.repository;

import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    boolean existsByProjectIdAndTitle(Long projectId, String title);

    Long id(Long id);

    List<Task> findAllTaskById(Long projectId);

    @Query("""
        SELECT t FROM Task t
        WHERE t.project.id = :projectId
        AND ((:createUserId IS NULL OR t.createdUser.id = :createUserId)
        OR (:status IS NOT NULL AND :status <> 'TODO' AND t.status = :status)
        OR (:priority IS NOT NULL AND :priority <> 'MEDIUM' AND t.priority = :priority)
        OR (:from IS NOT NULL AND t.createdAt >= :from)
        OR (:to IS NOT NULL AND t.createdAt <= :to)
        )
""")
    List<Task> searchTasks(
            @Param("projectId") Long projectId,
            @Param("createUserId") Long createUserId,
            @Param("status") TaskStatus status,
            @Param("priority") PriorityStatus priority,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}

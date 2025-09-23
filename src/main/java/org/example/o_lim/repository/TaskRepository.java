package org.example.o_lim.repository;

import org.example.o_lim.common.enums.PriorityStatus;
import org.example.o_lim.common.enums.TaskStatus;
import org.example.o_lim.entity.Task;
import org.example.o_lim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    boolean existsByProjectIdAndTitle(Long projectId, String title);

    Long id(Long id);

    @Query("""
    SELECT t FROM Task t WHERE t.project.id = :projectId
""")
    List<Task> findAllTaskById(Long projectId);

    @Query("""
        SELECT t FROM Task t
        WHERE t.project.id = :projectId
        AND (:createUserId IS NULL OR t.createdUser.id = :createUserId)
        AND (:status IS NULL OR t.status = :status)
        AND (:priority IS NULL OR t.priority = :priority)
        AND (:from IS NULL OR t.createdAt >= :from)
        AND (:to IS NULL OR t.createdAt <= :to)
        AND (:dueDate IS NULL OR t.dueDate = :dueDate)
""")
    List<Task> searchTasks(
            @Param("projectId") Long projectId,
            @Param("createUserId") Long createUserId,
            @Param("status") TaskStatus status,
            @Param("priority") PriorityStatus priority,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("dueDate") LocalDate dueDate
    );

//    List<Task> findByCreatedUser(Long createdUserId);
}

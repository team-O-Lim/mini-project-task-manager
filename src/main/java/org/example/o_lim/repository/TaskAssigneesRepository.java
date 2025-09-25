package org.example.o_lim.repository;

import org.example.o_lim.entity.TaskAssignees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskAssigneesRepository extends JpaRepository<TaskAssignees, Long> {

    @Query("""
        SELECT t FROM TaskAssignees t
        JOIN FETCH t.assignees
        WHERE t.task.id = :taskId
    """)
    List<TaskAssignees> findByTaskIdWithUser(@Param("taskId") Long taskId);

//    @Query("""
//        SELECT CASE WHEN COUNT(ta) > 0 THEN true ELSE false END
//            FROM TaskAssignees ta
//            JOIN ta.assignees u
//            WHERE ta.task.id = :taskId AND u.loginId = :loginId
//    """)
//    boolean existsByTaskIdAndAssigneesLoginId(Long taskId, String loginId);
}

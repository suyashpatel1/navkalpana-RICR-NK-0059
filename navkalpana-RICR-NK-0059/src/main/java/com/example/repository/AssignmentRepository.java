
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Assignment;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByBatchId(Long batchId);
}

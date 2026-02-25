package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.AssignmentSubmission;
import java.util.List;

public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {

    List<AssignmentSubmission> findByAssignmentId(Long assignmentId);

    List<AssignmentSubmission> findByStudentId(Long studentId);
}
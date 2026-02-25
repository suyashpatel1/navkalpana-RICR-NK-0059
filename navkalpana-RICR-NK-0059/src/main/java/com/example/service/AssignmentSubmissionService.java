
package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.repository.*;
import com.example.model.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionService {

    private final AssignmentSubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;

    public AssignmentSubmission submitAssignment(Long assignmentId, Long studentId, String link) {

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        AssignmentSubmission submission = AssignmentSubmission.builder()
                .assignment(assignment)
                .student(student)
                .submissionLink(link)
                .status("SUBMITTED")
                .submittedAt(LocalDateTime.now())
                .build();

        return submissionRepository.save(submission);
    }

    public AssignmentSubmission evaluate(Long submissionId, Double marks, String feedback) {

        AssignmentSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setMarks(marks);
        submission.setFeedback(feedback);
        submission.setStatus("EVALUATED");

        return submissionRepository.save(submission);
    }

    public List<AssignmentSubmission> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }
}

package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.repository.*;
import com.example.model.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final BatchRepository batchRepository;

    public Assignment createAssignment(Assignment assignment, Long batchId) {

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        assignment.setBatch(batch);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByBatch(Long batchId) {
        return assignmentRepository.findByBatchId(batchId);
    }

    public Assignment updateAssignment(Long id, Assignment updated) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assignment.setTitle(updated.getTitle());
        assignment.setDescription(updated.getDescription());
        assignment.setDueDate(updated.getDueDate());

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}
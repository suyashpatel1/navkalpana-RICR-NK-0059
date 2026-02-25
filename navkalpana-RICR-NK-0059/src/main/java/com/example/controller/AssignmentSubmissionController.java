
package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.service.AssignmentSubmissionService;
import com.example.model.AssignmentSubmission;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class AssignmentSubmissionController {

    private final AssignmentSubmissionService service;

    @PostMapping("/submit")
    public AssignmentSubmission submit(@RequestParam Long assignmentId,
                                       @RequestParam Long studentId,
                                       @RequestParam String link) {
        return service.submitAssignment(assignmentId, studentId, link);
    }

    @PutMapping("/evaluate/{submissionId}")
    public AssignmentSubmission evaluate(@PathVariable Long submissionId,
                                         @RequestParam Double marks,
                                         @RequestParam String feedback) {
        return service.evaluate(submissionId, marks, feedback);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<AssignmentSubmission> getByAssignment(@PathVariable Long assignmentId) {
        return service.getSubmissionsByAssignment(assignmentId);
    }
}
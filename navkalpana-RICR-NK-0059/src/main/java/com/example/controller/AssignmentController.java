
package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.service.AssignmentService;
import com.example.model.Assignment;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService service;

    @PostMapping("/{batchId}")
    public Assignment create(@RequestBody Assignment assignment,
                             @PathVariable Long batchId) {
        return service.createAssignment(assignment, batchId);
    }

    @GetMapping("/batch/{batchId}")
    public List<Assignment> getByBatch(@PathVariable Long batchId) {
        return service.getAssignmentsByBatch(batchId);
    }

    @PutMapping("/{id}")
    public Assignment update(@PathVariable Long id,
                             @RequestBody Assignment assignment) {
        return service.updateAssignment(id, assignment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAssignment(id);
    }
}
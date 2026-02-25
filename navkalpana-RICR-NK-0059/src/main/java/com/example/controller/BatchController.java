
package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.BatchRequest;
import com.example.dto.BatchResponse;
import com.example.service.BatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService service;

    // Create a new batch
    @PostMapping
    public ResponseEntity<BatchResponse> create(@RequestBody BatchRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    // Get all batches, optionally filter by courseId or status
    @GetMapping
    public ResponseEntity<List<BatchResponse>> getAll(
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.getAll(courseId, status));
    }

    // Update a batch
    @PutMapping("/{id}")
    public ResponseEntity<BatchResponse> update(
            @PathVariable Long id,
            @RequestBody BatchRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    // Delete a batch
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
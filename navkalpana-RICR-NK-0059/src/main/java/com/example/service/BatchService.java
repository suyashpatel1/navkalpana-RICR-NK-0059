
package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.BatchRequest;
import com.example.dto.BatchResponse;
import com.example.model.Batch;
import com.example.model.BatchStatus;
import com.example.model.Course;
import com.example.repository.BatchRepository;
import com.example.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;

    // CREATE
    public BatchResponse create(BatchRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Batch batch = new Batch();
        batch.setBatchName(request.getBatchName());
        batch.setBatchType(request.getBatchType());
        batch.setStartDate(request.getStartDate());
        batch.setEndDate(request.getEndDate());
        batch.setStatus(convertToStatus(request.getStatus()));
        batch.setProgressPercentage(request.getProgressPercentage() != null ? request.getProgressPercentage() : 0.0);
        batch.setType(request.getType());
        batch.setCourse(course);

        Batch saved = batchRepository.save(batch);

        return mapToResponse(saved);
    }

    // GET ALL WITH OPTIONAL FILTERS
    public List<BatchResponse> getAll(Long courseId, String statusStr) {
        List<Batch> batches = batchRepository.findAll();

        // Filter by courseId if provided
        if (courseId != null) {
            batches = batches.stream()
                    .filter(b -> b.getCourse() != null && b.getCourse().getId().equals(courseId))
                    .collect(Collectors.toList());
        }

        // Filter by status if provided
        if (statusStr != null && !statusStr.isEmpty()) {
            BatchStatus status = convertToStatus(statusStr);
            batches = batches.stream()
                    .filter(b -> b.getStatus() == status)
                    .collect(Collectors.toList());
        }

        return batches.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE
    public BatchResponse update(Long id, BatchRequest request) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        batch.setBatchName(request.getBatchName());
        batch.setBatchType(request.getBatchType());
        batch.setStartDate(request.getStartDate());
        batch.setEndDate(request.getEndDate());
        batch.setStatus(convertToStatus(request.getStatus()));
        batch.setProgressPercentage(request.getProgressPercentage() != null ? request.getProgressPercentage() : 0.0);
        batch.setType(request.getType());

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        batch.setCourse(course);

        Batch updated = batchRepository.save(batch);
        return mapToResponse(updated);
    }

    // DELETE
    public void delete(Long id) {
        batchRepository.deleteById(id);
    }

    // HELPER: safe conversion to enum
    private BatchStatus convertToStatus(String status) {
        if (status == null) return BatchStatus.UPCOMING;
        try {
            return BatchStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return BatchStatus.UPCOMING;
        }
    }

    // HELPER: map entity -> response
    private BatchResponse mapToResponse(Batch batch) {
        Course course = batch.getCourse();
        return BatchResponse.builder()
                .id(batch.getId())
                .batchName(batch.getBatchName())
                .batchType(batch.getBatchType())
                .status(batch.getStatus() != null ? batch.getStatus().name() : "UPCOMING")
                .progressPercentage(batch.getProgressPercentage())
                .type(batch.getType())
                .startDate(batch.getStartDate())
                .endDate(batch.getEndDate())
                .courseId(course != null ? course.getId() : null)
                .courseName(course != null ? course.getCourseName() : null)
                .build();
    }
}

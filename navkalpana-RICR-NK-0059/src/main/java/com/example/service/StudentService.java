
package com.example.service;

import com.example.dto.StudentRequest;
import com.example.dto.StudentResponse;
import com.example.model.Student;
import com.example.model.Batch;
import com.example.repository.StudentRepository;
import com.example.repository.BatchRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;

    // ✅ Create Student safely
    public StudentResponse create(StudentRequest request) {

        if (request.getBatchId() == null) {
            throw new IllegalArgumentException("Batch ID must not be null");
        }

        Batch batch = batchRepository.findById(request.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found with ID: " + request.getBatchId()));

        Student student = Student.builder()
                .name(request.getName())
                .enrollmentId(request.getEnrollmentId())
                .email(request.getEmail())
                .batch(batch)
                .build();

        Student savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    // ✅ Get All Students
    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ Get Student By ID safely
    public StudentResponse getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Student ID must not be null");
        }

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        return mapToResponse(student);
    }

    // ✅ Delete Student safely
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Student ID must not be null");
        }

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);
    }

    // 🔥 Map Student → StudentResponse
    private StudentResponse mapToResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .enrollmentId(student.getEnrollmentId())
                .email(student.getEmail())
                .batchId(student.getBatch() != null ? student.getBatch().getId() : null)
                .build();
    }
}
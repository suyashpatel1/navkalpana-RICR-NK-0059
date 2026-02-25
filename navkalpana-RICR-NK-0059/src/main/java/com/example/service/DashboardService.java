
package com.example.service;

import com.example.dto.DashboardResponse;
import com.example.repository.StudentRepository;
import com.example.repository.BatchRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;

   
    public DashboardResponse summary() {

        long totalStudents = studentRepository.count();
        long totalBatches = batchRepository.count();

        
        long activeCourses = batchRepository.count();

        return DashboardResponse.builder()
                .totalStudents(totalStudents)
                .totalBatches(totalBatches)
                .activeCourses(activeCourses)
                .build();
    }
}


package com.example.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BatchRequest {
    private String batchName;           // maps to batch_name
    private String batchType;           // maps to batch_type
    private LocalDate startDate;        // maps to start_date
    private LocalDate endDate;          // maps to end_date
    private String status;              // maps to status (ONGOING, UPCOMING, COMPLETED)
    private Double progress;            // maps to progress column
    private Double progressPercentage;  // maps to progress_percentage
    private String type;                // maps to type column (online/offline)
    private Integer totalStudents;      // maps to total_students
    private Long courseId;              // maps to course_id
}
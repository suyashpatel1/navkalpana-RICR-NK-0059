
package com.example.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BatchResponse {
    private Long id;
    private String batchName;
    private String batchType;
    private String status;
    private Double progress;             // database ka progress
    private Double progressPercentage;   // percentage field
    private String type;                 // online/offline
    private Integer totalStudents;       // total_students
    private LocalDate startDate;         // start_date
    private LocalDate endDate;           // end_date
    private Long courseId;    
    private String courseName;// course_id
}
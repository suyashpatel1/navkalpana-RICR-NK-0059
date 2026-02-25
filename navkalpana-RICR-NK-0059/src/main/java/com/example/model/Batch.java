
package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "batch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_name") // DB column
    private String batchName;

    @Column(name = "batch_type")
    private String batchType;

    @Column(name = "progress")
    private Double progress = 0.0; // default 0

    @Column(name = "progress_percentage")
    private Double progressPercentage = 0.0; // default 0

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BatchStatus status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "type")
    private String type; // DB me column hai

    @Column(name = "total_students")
    private Integer totalStudents = 0;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}





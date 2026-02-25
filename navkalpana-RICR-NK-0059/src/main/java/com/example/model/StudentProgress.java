package com.example.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double attendancePercentage;
    private Double assignmentAverage;
    private Double quizAverage;
    private Double overallGrowthIndex;

    @OneToOne
    private Student student;
}
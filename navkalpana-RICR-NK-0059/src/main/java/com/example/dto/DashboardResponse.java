package com.example.dto;


import lombok.*;


@AllArgsConstructor
@Data
@Builder
public class DashboardResponse {

    private long totalStudents;
    private long totalBatches;
    private long activeCourses;
}
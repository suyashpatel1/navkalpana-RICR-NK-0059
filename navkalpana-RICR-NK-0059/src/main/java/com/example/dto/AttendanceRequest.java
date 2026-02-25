package com.example.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AttendanceRequest {
    private Long studentId;
    private Long batchId;
    private String status;
    private String remarks;
    private LocalDate date;
}
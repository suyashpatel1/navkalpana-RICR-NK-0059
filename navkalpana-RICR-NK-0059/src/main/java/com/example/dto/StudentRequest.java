package com.example.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class StudentRequest {
    private String name;
    private String enrollmentId;
    private String email;
    private Long batchId;
}
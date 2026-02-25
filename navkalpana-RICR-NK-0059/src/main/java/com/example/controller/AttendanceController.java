package com.example.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.AttendanceRequest;
import com.example.model.AttendanceStatus;
import com.example.service.AttendanceService;



import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    // ✅ Mark Attendance
    @PostMapping
    public ResponseEntity<String> mark(
            @RequestBody AttendanceRequest request) {

        service.mark(request);
        return ResponseEntity.ok("Attendance Marked Successfully");
    }

    // ✅ Get Attendance Percentage
    @GetMapping("/percentage/{studentId}")
    public ResponseEntity<Double> percentage(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.calculateAttendancePercentage(studentId)
        );
    }

    // ✅ Get Present Count
    @GetMapping("/present/{studentId}")
    public ResponseEntity<Long> presentCount(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.getCountByStatus(studentId, AttendanceStatus.PRESENT)
        );
    }

    // ✅ Get Absent Count
    @GetMapping("/absent/{studentId}")
    public ResponseEntity<Long> absentCount(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.getCountByStatus(studentId, AttendanceStatus.ABSENT)
        );
    }
}
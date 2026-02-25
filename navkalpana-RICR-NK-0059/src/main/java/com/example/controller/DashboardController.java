
package com.example.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.DashboardResponse;
import com.example.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    // ✅ Dashboard Summary
    @GetMapping("/summary")
    public ResponseEntity<DashboardResponse> getSummary() {

        return ResponseEntity.ok(service.summary());
    }
}
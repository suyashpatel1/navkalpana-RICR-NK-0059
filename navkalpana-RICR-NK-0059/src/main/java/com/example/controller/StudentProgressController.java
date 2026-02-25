
package com.example.controller;

import com.example.service.StudentProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin
public class StudentProgressController {

    private final StudentProgressService progressService;

    // 🔥 Calculate / Update Progress
    @PostMapping("/update/{studentId}")
    public String updateProgress(@PathVariable Long studentId) {
        progressService.updateProgress(studentId);
        return "Progress Updated Successfully!";
    }

}
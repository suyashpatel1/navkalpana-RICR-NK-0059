
package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.service.StudentAnswerService;
import com.example.model.StudentAnswer;
import java.util.List;

@RestController
@RequestMapping("/api/student-answers")
@RequiredArgsConstructor
public class StudentAnswerController {

    private final StudentAnswerService service;

    @PostMapping
    public StudentAnswer save(@RequestParam Long attemptId,
                              @RequestParam Long questionId,
                              @RequestParam String selectedAnswer) {
        return service.saveAnswer(attemptId, questionId, selectedAnswer);
    }

    @GetMapping("/{attemptId}")
    public List<StudentAnswer> getByAttempt(@PathVariable Long attemptId) {
        return service.getAnswersByAttempt(attemptId);
    }
}
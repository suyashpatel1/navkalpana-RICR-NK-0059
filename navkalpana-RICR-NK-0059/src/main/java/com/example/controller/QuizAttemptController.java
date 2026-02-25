package com.example.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.QuizAttempt;
import com.example.service.QuizAttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quiz-attempt")
@RequiredArgsConstructor
public class QuizAttemptController {

    private final QuizAttemptService service;

    @PostMapping("/submit")
    public QuizAttempt submit(@RequestParam Long quizId,
                              @RequestParam Long studentId,
                              @RequestBody Map<Long, String> answers) {
        return service.submitQuiz(quizId, studentId, answers);
    }
}
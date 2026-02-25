package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Quiz;
import com.example.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService service;

    @PostMapping("/{batchId}")
    public Quiz create(@RequestBody Quiz quiz,
                       @PathVariable Long batchId) {
        return service.createQuiz(quiz, batchId);
    }

    @GetMapping
    public List<Quiz> getAll() {
        return service.getAllQuizzes();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteQuiz(id);
    }
}

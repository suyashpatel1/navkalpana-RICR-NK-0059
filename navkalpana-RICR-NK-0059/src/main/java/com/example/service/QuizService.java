package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Batch;
import com.example.model.Quiz;
import com.example.repository.BatchRepository;
import com.example.repository.QuizRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final BatchRepository batchRepository;

    public Quiz createQuiz(Quiz quiz, Long batchId) {

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        quiz.setBatch(batch);
        quiz.setActive(true);

        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
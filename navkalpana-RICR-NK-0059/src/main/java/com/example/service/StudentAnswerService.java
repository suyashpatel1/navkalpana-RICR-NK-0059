
package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.repository.*;
import com.example.model.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentAnswerService {

    private final StudentAnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository attemptRepository;

    // Save single answer (manual use)
    public StudentAnswer saveAnswer(Long attemptId,
                                    Long questionId,
                                    String selectedAnswer) {

        QuizAttempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        StudentAnswer answer = StudentAnswer.builder()
                .quizAttempt(attempt)
                .question(question)
                .selectedAnswer(selectedAnswer)
                .build();

        return answerRepository.save(answer);
    }

    // Get answers by Attempt
    public List<StudentAnswer> getAnswersByAttempt(Long attemptId) {

        return answerRepository.findAll()
                .stream()
                .filter(a -> a.getQuizAttempt().getId().equals(attemptId))
                .toList();
    }
}
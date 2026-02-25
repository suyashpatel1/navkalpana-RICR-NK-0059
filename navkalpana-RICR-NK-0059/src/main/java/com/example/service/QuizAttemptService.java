package com.example.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.model.Question;
import com.example.model.Quiz;
import com.example.model.QuizAttempt;
import com.example.model.Student;
import com.example.model.StudentAnswer;
import com.example.repository.QuestionRepository;
import com.example.repository.QuizAttemptRepository;
import com.example.repository.QuizRepository;
import com.example.repository.StudentAnswerRepository;
import com.example.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;
    private final QuizAttemptRepository attemptRepository;
    private final StudentAnswerRepository answerRepository;

    public QuizAttempt submitQuiz(Long quizId,
                                  Long studentId,
                                  Map<Long, String> answers) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .student(student)
                .attemptedAt(LocalDateTime.now())
                .build();

        attempt = attemptRepository.save(attempt);

        double score = 0;

        for (Map.Entry<Long, String> entry : answers.entrySet()) {

            Question question = questionRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            String selected = entry.getValue();

            if (question.getCorrectAnswer().equals(selected)) {
                score++;
            }

            StudentAnswer answer = StudentAnswer.builder()
                    .question(question)
                    .quizAttempt(attempt)
                    .selectedAnswer(selected)
                    .build();

            answerRepository.save(answer);
        }

        attempt.setScore(score);
        return attemptRepository.save(attempt);
    }
}
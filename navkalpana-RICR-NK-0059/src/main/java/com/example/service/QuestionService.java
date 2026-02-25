
package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.repository.*;
import com.example.model.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    // Add Question to Quiz
    public Question addQuestion(Long quizId, Question question) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    // Get Questions by Quiz
    public List<Question> getQuestionsByQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    // Update Question
    public Question updateQuestion(Long id, Question updated) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuestionText(updated.getQuestionText());
        question.setOptionA(updated.getOptionA());
        question.setOptionB(updated.getOptionB());
        question.setOptionC(updated.getOptionC());
        question.setOptionD(updated.getOptionD());
        question.setCorrectAnswer(updated.getCorrectAnswer());

        return questionRepository.save(question);
    }

    // Delete Question
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
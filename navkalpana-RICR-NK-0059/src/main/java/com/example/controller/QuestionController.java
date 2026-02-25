
package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.service.QuestionService;
import com.example.model.Question;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

    @PostMapping("/{quizId}")
    public Question add(@PathVariable Long quizId,
                        @RequestBody Question question) {
        return service.addQuestion(quizId, question);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getByQuiz(@PathVariable Long quizId) {
        return service.getQuestionsByQuiz(quizId);
    }

    @PutMapping("/{id}")
    public Question update(@PathVariable Long id,
                           @RequestBody Question question) {
        return service.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteQuestion(id);
    }
}
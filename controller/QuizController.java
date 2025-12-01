
package com.example.onlinecourse.controller;

import com.example.onlinecourse.model.Question;
import com.example.onlinecourse.model.QuizSubmission;
import com.example.onlinecourse.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/random")
    public ResponseEntity<Question> random() {
        return ResponseEntity.ok(quizService.getRandomQuestion());
    }

    @GetMapping("/random/{count}")
    public ResponseEntity<List<Question>> randomN(@PathVariable int count) {
        return ResponseEntity.ok(quizService.getRandomQuestions(count));
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizSubmission> submit(@RequestBody Map<String, String> payload) {
        Long userId = Long.parseLong(payload.get("userId"));
        Long questionId = Long.parseLong(payload.get("questionId"));
        String answer = payload.get("answer");
        return ResponseEntity.ok(quizService.submitAnswer(userId, questionId, answer));
    }
}

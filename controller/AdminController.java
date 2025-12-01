
package com.example.onlinecourse.controller;

import com.example.onlinecourse.model.Question;
import com.example.onlinecourse.model.QuizSubmission;
import com.example.onlinecourse.repository.QuestionRepository;
import com.example.onlinecourse.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizSubmissionRepository submissionRepository;

    @PostMapping("/questions")
    public ResponseEntity<Question> addQuestion(@RequestBody Question q) {
        return ResponseEntity.ok(questionRepository.save(q));
    }

    @GetMapping("/submissions")
    public ResponseEntity<List<QuizSubmission>> submissions() {
        return ResponseEntity.ok(submissionRepository.findAll());
    }
}

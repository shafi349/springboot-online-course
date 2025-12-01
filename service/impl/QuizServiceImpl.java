
package com.example.onlinecourse.service.impl;

import com.example.onlinecourse.exception.ResourceNotFoundException;
import com.example.onlinecourse.model.Question;
import com.example.onlinecourse.model.QuizSubmission;
import com.example.onlinecourse.model.User;
import com.example.onlinecourse.repository.QuestionRepository;
import com.example.onlinecourse.repository.QuizSubmissionRepository;
import com.example.onlinecourse.repository.UserRepository;
import com.example.onlinecourse.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizSubmissionRepository submissionRepository;

    @Override
    public Question getRandomQuestion() {
        List<Question> all = questionRepository.findAll();
        if (all.isEmpty()) throw new ResourceNotFoundException("No questions available");
        Collections.shuffle(all);
        return all.get(0);
    }

    @Override
    public List<Question> getRandomQuestions(int count) {
        List<Question> all = questionRepository.findAll();
        Collections.shuffle(all);
        return all.subList(0, Math.min(count, all.size()));
    }

    @Override
    public QuizSubmission submitAnswer(Long userId, Long questionId, String answer) {
        Question q = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        boolean correct = q.getAnswer() != null && q.getAnswer().trim().equalsIgnoreCase(answer.trim());
        QuizSubmission s = new QuizSubmission();
        s.setQuestionText(q.getQuestionText());
        s.setSubmittedAnswer(answer);
        s.setCorrect(correct);
        s.setSubmittedAt(LocalDateTime.now());
        s.setUser(u);
        return submissionRepository.save(s);
    }
}

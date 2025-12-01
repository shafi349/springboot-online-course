
package com.example.onlinecourse.service;

import com.example.onlinecourse.model.Question;
import com.example.onlinecourse.model.QuizSubmission;

import java.util.List;

public interface QuizService {
    Question getRandomQuestion();
    List<Question> getRandomQuestions(int count);
    QuizSubmission submitAnswer(Long userId, Long questionId, String answer);
}

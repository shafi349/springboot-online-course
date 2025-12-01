
package com.example.onlinecourse.repository;

import com.example.onlinecourse.model.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    List<QuizSubmission> findByUserId(Long userId);
}

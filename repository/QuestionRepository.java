
package com.example.onlinecourse.repository;

import com.example.onlinecourse.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

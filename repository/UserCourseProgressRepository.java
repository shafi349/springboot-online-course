
package com.example.onlinecourse.repository;

import com.example.onlinecourse.model.UserCourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCourseProgressRepository extends JpaRepository<UserCourseProgress, Long> {
    Optional<UserCourseProgress> findByUserIdAndCourseId(Long userId, Long courseId);
}

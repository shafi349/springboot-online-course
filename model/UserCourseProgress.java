
package com.example.onlinecourse.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_course_progress")
public class UserCourseProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long courseId;
    private Integer completedChapters = 0;
    private Integer totalChapters = 0;
}

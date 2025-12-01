
package com.example.onlinecourse.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_submissions")
public class QuizSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String submittedAnswer;
    private boolean correct;
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

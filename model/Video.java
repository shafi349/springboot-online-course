
package com.example.onlinecourse.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url; // video URL or path

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}

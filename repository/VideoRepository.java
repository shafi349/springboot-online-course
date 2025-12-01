
package com.example.onlinecourse.repository;

import com.example.onlinecourse.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}

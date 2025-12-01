
package com.example.onlinecourse.config;

import com.example.onlinecourse.model.*;
import com.example.onlinecourse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_USER"));
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));
            userRepository.save(admin);
        }

        if (courseRepository.count() == 0) {
            Course c = new Course();
            c.setTitle("Sample Course");
            c.setDescription("A sample course with chapters and videos");

            Chapter ch1 = new Chapter();
            ch1.setTitle("Chapter 1");
            ch1.setOrderNumber(1);

            Video v1 = new Video();
            v1.setTitle("Intro Video");
            v1.setUrl("https://example.com/video1.mp4");

            ch1.setVideos(List.of(v1));
            ch1.setCourse(c);

            c.setChapters(List.of(ch1));
            courseRepository.save(c);
        }

        if (questionRepository.count() == 0) {
            questionRepository.save(new Question(null, "What is Java?", "A programming language", null));
            questionRepository.save(new Question(null, "What is Spring Boot?", "Framework", null));
        }
    }
}

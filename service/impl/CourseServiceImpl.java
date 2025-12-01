
package com.example.onlinecourse.service.impl;

import com.example.onlinecourse.exception.ResourceNotFoundException;
import com.example.onlinecourse.model.Course;
import com.example.onlinecourse.repository.CourseRepository;
import com.example.onlinecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}

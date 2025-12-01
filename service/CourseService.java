
package com.example.onlinecourse.service;

import com.example.onlinecourse.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
}

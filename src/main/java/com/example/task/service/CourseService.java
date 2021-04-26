package com.example.task.service;

import com.example.task.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(int id);
    void save(Course course);
    void deleteById(int id);
}

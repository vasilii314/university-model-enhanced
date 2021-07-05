package com.example.task.service.impl;

import com.example.task.entity.*;
import com.example.task.exception.custom.CourseNotFoundException;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;
import com.example.task.repository.CourseRepository;
import com.example.task.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findCoursesCriteria(CourseFilterRequest filter) {
        List<Course> courses = courseRepository.findCoursesCriteria(filter);
        if (courses.size() == 0) {
            throw new CourseNotFoundException(filter);
        }
        return courses;
    }

    @Override
    public void addCourseCriteria(CourseAddRequest filter) {
        courseRepository.addCourseCriteria(filter);
    }

    @Override
    public void deleteCourseCriteria(CourseFilterRequest filter) {
        courseRepository.deleteCourseCriteria(filter);
    }

    @Override
    public void updateCourseCriteria(CourseAddRequest filter) {
        courseRepository.updateCourseCriteria(filter);
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }
}

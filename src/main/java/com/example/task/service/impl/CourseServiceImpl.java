package com.example.task.service.impl;

import com.example.task.entity.*;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;
import com.example.task.json.responses.CourseDTO;
import com.example.task.repository.CourseRepository;
import com.example.task.repository.custom.CourseRepositoryCustom;
import com.example.task.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseRepositoryCustom courseRepositoryCustom;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseRepositoryCustom courseRepositoryCustom) {
        this.courseRepository = courseRepository;
        this.courseRepositoryCustom = courseRepositoryCustom;
    }

    @Override
    public List<CourseDTO> findCourses(CourseFilterRequest filter) {
        return courseRepositoryCustom
                .findCourses(filter)
                .stream()
                .map(course -> new CourseDTO(course.getId(),
                        course.getName(),
                        course.getDuration(),
                        course.getDepartment().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void addCourse(CourseAddRequest filter) {
        courseRepositoryCustom.addCourse(filter);
    }

    @Override
    public void deleteCourse(CourseFilterRequest filter) {
        courseRepositoryCustom.deleteCourse(filter);
    }

    @Override
    public void updateCourse(CourseAddRequest filter) {
        courseRepositoryCustom.updateCourse(filter);
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

package com.example.task.service;

import com.example.task.entity.Course;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;
import com.example.task.json.responses.CourseDTO;

import java.util.List;

public interface CourseService extends Service<Course> {
    List<CourseDTO> findCourses(CourseFilterRequest filter);

    void addCourse(CourseAddRequest filter);

    void deleteCourse(CourseFilterRequest filter);

    void updateCourse(CourseAddRequest filter);
}

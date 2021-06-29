package com.example.task.service;

import com.example.task.entity.Course;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;

import java.util.List;

public interface CourseService extends Service<Course> {
    List<Course> findCoursesCriteria(CourseFilterRequest filter);
    void addCourseCriteria(CourseAddRequest filter);
    void deleteCourseCriteria(CourseFilterRequest filter);
    void updateCourseCriteria(CourseAddRequest filter);
}

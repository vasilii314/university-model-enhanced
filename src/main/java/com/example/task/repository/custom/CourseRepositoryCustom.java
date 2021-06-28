package com.example.task.repository.custom;

import com.example.task.entity.Course;
import com.example.task.json.filters.CourseFilterRequest;
import com.example.task.service.Service;

import java.util.List;

public interface CourseRepositoryCustom {
    List<Course> findCoursesCriteria(CourseFilterRequest filter);
    void addCourseCriteria(CourseFilterRequest filter);
    void deleteCourseCriteria(CourseFilterRequest filter);
    void updateCourseCriteria(CourseFilterRequest filter);
}

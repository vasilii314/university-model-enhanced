package com.example.task.repository.custom;

import com.example.task.entity.Course;
import com.example.task.json.requests.filters.CourseFilterRequest;

import java.util.List;

public interface CourseRepositoryCustom {
    List<Course> findCoursesCriteria(CourseFilterRequest filter);
    void addCourseCriteria(CourseFilterRequest filter);
    void deleteCourseCriteria(CourseFilterRequest filter);
    void updateCourseCriteria(CourseFilterRequest filter);
}

package com.example.task.repository.custom;

import com.example.task.entity.Course;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryCustom {
    List<Course> findCourses(CourseFilterRequest filter);

    void addCourse(CourseAddRequest filter);

    void deleteCourse(CourseFilterRequest filter);

    void updateCourse(CourseAddRequest filter);
}

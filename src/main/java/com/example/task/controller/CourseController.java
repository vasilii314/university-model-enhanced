package com.example.task.controller;

import com.example.task.entity.Course;
import com.example.task.json.filters.CourseFilterRequest;
import com.example.task.json.responses.CourseDTO;
import com.example.task.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    private List<CourseDTO> getCourses(@RequestBody CourseFilterRequest req) {
        List<Course> coursesRaw = courseService.findCoursesCriteria(req);
        List<CourseDTO> courses = coursesRaw
                .stream()
                .map(course -> new CourseDTO(course.getName(), course.getDuration(), course.getDepartment().getName()))
                .collect(Collectors.toList());
        return courses;
    }

    @PostMapping("/courses")
    private void addCourse(@RequestBody CourseFilterRequest req) {
        courseService.addCourseCriteria(req);
    }

    @DeleteMapping("/courses")
    private void deleteCourse(@RequestBody CourseFilterRequest req) {
        courseService.deleteCourseCriteria(req);
    }

    @PatchMapping("/courses")
    private void updateCourse(@RequestBody CourseFilterRequest req) {
        courseService.updateCourseCriteria(req);
    }
}

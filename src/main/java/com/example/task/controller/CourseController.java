package com.example.task.controller;

import com.example.task.entity.Course;
import com.example.task.json.filters.CourseFilterRequest;
import com.example.task.json.responses.CourseDTO;
import com.example.task.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/courses")
    private ResponseEntity<List<CourseDTO>> getCoursesCriteria(@RequestBody CourseFilterRequest req) {
        List<Course> coursesRaw = courseService.findCoursesCriteria(req);
        if (coursesRaw.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<CourseDTO> courses = coursesRaw
                .stream()
                .map(course -> new CourseDTO(course.getId(), course.getName(), course.getDuration(), course.getDepartment().getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/add-course")
    private ResponseEntity<CourseDTO> addCourseCriteria(@RequestBody CourseFilterRequest req) {
        try {
            courseService.addCourseCriteria(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/courses")
    private ResponseEntity<CourseDTO> deleteCourseCriteria(@RequestBody CourseFilterRequest req) {
        try {
            courseService.deleteCourseCriteria(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/courses")
    private ResponseEntity<CourseDTO> updateCourseCriteria(@RequestBody CourseFilterRequest req) {
        try {
            courseService.updateCourseCriteria(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

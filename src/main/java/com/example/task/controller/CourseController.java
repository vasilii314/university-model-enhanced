package com.example.task.controller;

import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;
import com.example.task.json.responses.CourseDTO;
import com.example.task.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(@RequestBody CourseFilterRequest req) {
        return ResponseEntity.ok(courseService.findCourses(req));
    }

    @PostMapping("/add-course")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseAddRequest req) {
        courseService.addCourse(req);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/courses")
    public ResponseEntity<CourseDTO> deleteCourse(@RequestBody CourseFilterRequest req) {
        courseService.deleteCourse(req);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/courses")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseAddRequest req) {
        courseService.updateCourse(req);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> deleteCourseById(@PathVariable Integer id) {
        courseService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}

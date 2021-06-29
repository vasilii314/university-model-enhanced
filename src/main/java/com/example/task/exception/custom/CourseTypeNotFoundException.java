package com.example.task.exception.custom;

public class CourseTypeNotFoundException extends RuntimeException {
    public CourseTypeNotFoundException() {
        super("Course type not found");
    }
}

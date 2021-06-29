package com.example.task.exception.custom;

public class StudentGradeNotFoundException extends RuntimeException {
    public StudentGradeNotFoundException() {
        super("Grades not found");
    }
}

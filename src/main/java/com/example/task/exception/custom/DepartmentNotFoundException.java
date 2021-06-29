package com.example.task.exception.custom;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super("Department not found");
    }
}

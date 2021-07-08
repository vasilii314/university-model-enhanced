package com.example.task.exception.custom;

public class SchoolNotFoundException extends RuntimeException {
    public SchoolNotFoundException() {
        super("School not found");
    }
}

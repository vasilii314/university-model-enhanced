package com.example.task.exception.custom;

import com.example.task.json.requests.filters.CourseFilterRequest;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(CourseFilterRequest filter) {
        super("No course found for the following request:\n" + filter);
    }
}

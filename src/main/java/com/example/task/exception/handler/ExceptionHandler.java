package com.example.task.exception.handler;

import com.example.task.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(SchoolNotFoundException.class)
    public ResponseEntity<?> handleSchoolNotFoundException(SchoolNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "School not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalException.class)
    public ResponseEntity<?> handleInternalException(InternalException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Internal error");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DeleteOrUpdateException.class)
    public ResponseEntity<?> handleSchoolDeleteOrUpdateException(DeleteOrUpdateException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Delete or update failed");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<?> handleCourseNotFoundException(CourseNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Course not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Bad request");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Internal error");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<?> handleDepartmentNotFoundException(DepartmentNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Department not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<?> handleGroupNotFoundException(GroupNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Group not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Student not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Employee not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(StudentGradeNotFoundException.class)
    public ResponseEntity<?> handleStudentGradeNotFoundException(StudentGradeNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Grades not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UpdatesObjectMissingException.class)
    public ResponseEntity<?> handleUpdatesObjectMissingException(UpdatesObjectMissingException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Bad request");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentDTO;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {

    private HumanService humanService;

    @Autowired
    public StudentController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@RequestBody @Valid StudentFilterRequest req) {
        List<Human> studentsRaw = humanService.findStudents(req);
        if (studentsRaw.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<StudentDTO> students = studentsRaw.stream().map(StudentDTO::toStudentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody @Valid StudentFilterRequest req) {
        try {
            humanService.addStudentCriteria(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/students/grades")
    public ResponseEntity<List<StudentGradeDTO>> getGrades(@RequestBody @Valid StudentFilterRequest req) {
        List<StudentGradeDTO> grades = humanService.getStudentGrades(req);
        if (grades.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grades);
    }

    @PostMapping("/students/grades")
    public ResponseEntity<?> addGrade(@RequestBody @Valid StudentFilterRequest req) {
        try {
            humanService.addStudentGrade(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/students")
    public ResponseEntity<?> deleteStudent(@RequestBody @Valid EmployeeFilterRequest req) {
        try {
            humanService.deleteEmployeeOrStudent(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/students")
    public ResponseEntity<?> updateStudent(@RequestBody @Valid EmployeeFilterRequest req) {
        try {
            humanService.updateEmployeeOrStudent(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

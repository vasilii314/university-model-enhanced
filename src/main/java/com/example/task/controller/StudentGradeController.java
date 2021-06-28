package com.example.task.controller;

import com.example.task.json.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentGradeController {

    private HumanService humanService;

    @Autowired
    public StudentGradeController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/grades")
    public ResponseEntity<List<StudentGradeDTO>> getGradesCriteria(@RequestBody @Valid StudentFilterRequest req) {
        List<StudentGradeDTO> grades = humanService.getStudentGradesCriteria(req);
        if (grades.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grades);
    }

    @PostMapping("/add-grade")
    public ResponseEntity<StudentGradeDTO> addGradeCriteria(@RequestBody @Valid StudentFilterRequest req) {
        try {
            humanService.addStudentGradeCriteria(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

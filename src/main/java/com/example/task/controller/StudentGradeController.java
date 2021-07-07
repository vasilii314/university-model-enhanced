package com.example.task.controller;

import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
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

    private final HumanService humanService;

    @Autowired
    public StudentGradeController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/grades")
    public ResponseEntity<List<StudentGradeDTO>> getGrades(@RequestBody @Valid StudentFilterRequest req) {
        return ResponseEntity.ok(humanService.getStudentGrades(req));
    }

    @PostMapping("/add-grade")
    public ResponseEntity<StudentGradeDTO> addGrade(@RequestBody @Valid StudentAddRequest req) {
        humanService.addStudentGrade(req);
        return ResponseEntity.status(201).build();
    }
}

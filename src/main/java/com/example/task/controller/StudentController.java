package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentDTO;
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

    @PostMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudentsCriteria(@RequestBody @Valid StudentFilterRequest req) {
        List<Human> studentsRaw = humanService.findStudentsCriteria(req);
        List<StudentDTO> students = studentsRaw.stream().map(StudentDTO::toStudentDTO).collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }

    @PostMapping("/add-student")
    public ResponseEntity<StudentDTO> addStudentCriteria(@RequestBody @Valid StudentFilterRequest req) {
            humanService.addStudentCriteria(req);
            return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/students")
    public ResponseEntity<StudentDTO> deleteStudentCriteria(@RequestBody @Valid EmployeeFilterRequest req) {
        try {
            humanService.deleteEmployeeOrStudentCriteria(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/students")
    public ResponseEntity<StudentDTO> updateStudentCriteria(@RequestBody @Valid EmployeeFilterRequest req) {
            humanService.updateEmployeeOrStudentCriteria(req);
            return ResponseEntity.status(204).build();
    }
}

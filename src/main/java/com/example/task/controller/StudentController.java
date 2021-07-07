package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
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

    private final HumanService humanService;

    @Autowired
    public StudentController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@RequestBody @Valid StudentFilterRequest req) {
        return ResponseEntity.ok(humanService.findStudents(req));
    }

    @PostMapping("/add-student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody @Valid StudentAddRequest req) {
            humanService.addStudent(req);
            return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/students")
    public ResponseEntity<StudentDTO> deleteStudent(@RequestBody @Valid EmployeeFilterRequest req) {
            humanService.deleteEmployeeOrStudent(req);
            return ResponseEntity.status(204).build();
    }

    @PatchMapping("/students")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody @Valid EmployeeAddRequest req) {
            humanService.updateEmployeeOrStudent(req);
            return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable Integer id) {
        humanService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}

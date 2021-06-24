package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeAddRequest;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.responses.EmployeeDTO;
import com.example.task.repository.HumanInUniversityRepository;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private HumanService humanService;
    private HumanInUniversityRepository humanInUniversityRepository;

    @Autowired
    public EmployeeController(HumanService humanService, HumanInUniversityRepository humanInUniversityRepository) {
        this.humanService = humanService;
        this.humanInUniversityRepository = humanInUniversityRepository;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestBody EmployeeFilterRequest req) {

        List<Human> peopleInUniversity = humanService.findEmployeesCriteria(req);
        if (peopleInUniversity.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<EmployeeDTO> employees =  peopleInUniversity
                .stream()
                .map(EmployeeDTO::toEmployeeDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeAddRequest req) {
        try {
            humanService.addEmployeeCriteria(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/employees")
    public ResponseEntity<?> deleteEmployee(@RequestBody EmployeeFilterRequest req) {
        try {
            humanService.deleteEmployeeOrStudent(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/employees")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeFilterRequest req) {
        try {
            humanService.updateEmployeeOrStudent(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

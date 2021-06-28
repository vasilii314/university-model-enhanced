package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.responses.EmployeeDTO;
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

    @Autowired
    public EmployeeController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesCriteria(@RequestBody EmployeeFilterRequest req) {

        List<Human> peopleInUniversity = humanService.findEmployeesCriteria(req);
        List<EmployeeDTO> employees =  peopleInUniversity
                .stream()
                .map(EmployeeDTO::toEmployeeDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/add-employee")
    public ResponseEntity<EmployeeDTO> addEmployeeCriteria(@RequestBody EmployeeAddRequest req) {
            humanService.addEmployeeCriteria(req);
            return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/employees")
    public ResponseEntity<EmployeeDTO> deleteEmployeeCriteria(@RequestBody EmployeeFilterRequest req) {
            humanService.deleteEmployeeOrStudentCriteria(req);
            return ResponseEntity.status(204).build();
    }

    @PatchMapping("/employees")
    public ResponseEntity<EmployeeDTO> updateEmployeeCriteria(@RequestBody EmployeeFilterRequest req) {
            humanService.updateEmployeeOrStudentCriteria(req);
            return ResponseEntity.status(204).build();
    }
}

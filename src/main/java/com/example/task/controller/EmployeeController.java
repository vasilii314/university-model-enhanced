package com.example.task.controller;

import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.responses.EmployeeDTO;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final HumanService humanService;

    @Autowired
    public EmployeeController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestBody EmployeeFilterRequest req) {
        return ResponseEntity.ok(humanService.findEmployees(req));
    }

    @PostMapping("/add-employee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeAddRequest req) {
        humanService.addEmployee(req);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/employees")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@RequestBody EmployeeFilterRequest req) {
        humanService.deleteEmployeeOrStudent(req);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/employees")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeAddRequest req) {
        humanService.updateEmployeeOrStudent(req);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable Integer id) {
        humanService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}

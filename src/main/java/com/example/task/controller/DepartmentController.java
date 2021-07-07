package com.example.task.controller;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;
import com.example.task.json.responses.DepartmentDTO;
import com.example.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> findDepartments(@RequestBody DepartmentFilterRequest req) {
        return ResponseEntity.ok(departmentService.findDepartments(req));
    }

    @PostMapping("/add-department")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentAddRequest req) {
            departmentService.addDepartment(req);
            return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/departments")
    public ResponseEntity<DepartmentDTO> deleteDepartment(@RequestBody DepartmentFilterRequest req) {
            departmentService.deleteDepartment(req);
            return ResponseEntity.status(204).build();
    }

    @PatchMapping("/departments")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentAddRequest req) {
            departmentService.updateDepartment(req);
            return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> deleteDepartmentById(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}

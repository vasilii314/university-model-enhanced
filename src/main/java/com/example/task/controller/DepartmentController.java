package com.example.task.controller;

import com.example.task.entity.Department;
import com.example.task.json.filters.DepartmentFilterRequest;
import com.example.task.json.responses.DepartmentDTO;
import com.example.task.service.DepartmentService;
import liquibase.pro.packaged.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> findDepartments(@RequestBody DepartmentFilterRequest req) {
        List<Department> departmentsRaw = departmentService.findDepartmentsCriteria(req);
        if (departmentsRaw.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<DepartmentDTO> departments =  departmentsRaw
                .stream()
                .map(DepartmentDTO::toDepartmentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/departments")
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentFilterRequest req) {
        try {
            departmentService.addDepartment(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/departments")
    public ResponseEntity<?> deleteDepartment(@RequestBody DepartmentFilterRequest req) {
        try {
            departmentService.deleteDepartment(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/departments")
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentFilterRequest req) {
        try {
            departmentService.updateDepartment(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

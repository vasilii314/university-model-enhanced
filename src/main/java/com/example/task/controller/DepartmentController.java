package com.example.task.controller;

import com.example.task.entity.Department;
import com.example.task.json.filters.DepartmentFilterRequest;
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

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> findDepartmentsCriteria(@RequestBody DepartmentFilterRequest req) {
        List<Department> departmentsRaw = departmentService.findDepartmentsCriteria(req);
        if (departmentsRaw.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(departmentsRaw.get(0).getId());
        List<DepartmentDTO> departments =  departmentsRaw
                .stream()
                .map(DepartmentDTO::toDepartmentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/add-department")
    public ResponseEntity<DepartmentDTO> addDepartmentCriteria(@RequestBody DepartmentFilterRequest req) {
        try {
            departmentService.addDepartmentCriteria(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/departments")
    public ResponseEntity<DepartmentDTO> deleteDepartmentCriteria(@RequestBody DepartmentFilterRequest req) {
        try {
            departmentService.deleteDepartmentCriteria(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/departments")
    public ResponseEntity<DepartmentDTO> updateDepartmentCriteria(@RequestBody DepartmentFilterRequest req) {
        try {
            departmentService.updateDepartmentCriteria(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

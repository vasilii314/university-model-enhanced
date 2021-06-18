package com.example.task.controller;

import com.example.task.entity.Department;
import com.example.task.json.filters.DepartmentFilterRequest;
import com.example.task.json.responses.DepartmentDTO;
import com.example.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DepartmentDTO> findDepartments(@RequestBody DepartmentFilterRequest req) {
        List<Department> departmentsRaw = departmentService.findDepartmentsCriteria(req);
        for (Department department : departmentsRaw) {
            System.out.println(department.getName());
        }

        List<DepartmentDTO> departments =  departmentsRaw
                .stream()
                .map(DepartmentDTO::toDepartmentDTO)
                .collect(Collectors.toList());
        return departments;
    }

    @PostMapping("/departments")
    public void addDepartment(@RequestBody DepartmentFilterRequest req) {
        departmentService.addDepartment(req);
    }

    @DeleteMapping("/departments")
    public void deleteDepartment(@RequestBody DepartmentFilterRequest req) {
        departmentService.deleteDepartment(req);
    }

    @PatchMapping("/departments")
    public void updateDepartment(@RequestBody DepartmentFilterRequest req) {
        departmentService.updateDepartment(req);
    }
}

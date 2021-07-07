package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;
import com.example.task.json.responses.DepartmentDTO;

import java.util.List;

public interface DepartmentService extends Service<Department> {
    List<DepartmentDTO> findDepartments(DepartmentFilterRequest filter);
    void addDepartment(DepartmentAddRequest filter);
    void deleteDepartment(DepartmentFilterRequest filter);
    void updateDepartment(DepartmentAddRequest filter);
}

package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.json.filters.DepartmentFilterRequest;

import java.util.List;

public interface DepartmentService extends Service<Department> {
    List<Department> findDepartmentsCriteria(DepartmentFilterRequest filter);
    void addDepartment(DepartmentFilterRequest filter);
    void deleteDepartment(DepartmentFilterRequest filter);
    void updateDepartment(DepartmentFilterRequest filter);
}

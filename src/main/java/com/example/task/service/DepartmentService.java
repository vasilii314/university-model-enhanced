package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;

import java.util.List;

public interface DepartmentService extends Service<Department> {
    List<Department> findDepartmentsCriteria(DepartmentFilterRequest filter);
    void addDepartmentCriteria(DepartmentFilterRequest filter);
    void deleteDepartmentCriteria(DepartmentFilterRequest filter);
    void updateDepartmentCriteria(DepartmentFilterRequest filter);
}

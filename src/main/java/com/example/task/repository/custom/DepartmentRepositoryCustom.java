package com.example.task.repository.custom;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<Department> findDepartments(DepartmentFilterRequest filter);
    void addDepartment(DepartmentAddRequest filter);
    void deleteDepartment(DepartmentFilterRequest filter);
    void updateDepartment(DepartmentAddRequest filter);
}

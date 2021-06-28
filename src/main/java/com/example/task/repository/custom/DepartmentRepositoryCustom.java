package com.example.task.repository.custom;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<Department> findDepartmentsCriteria(DepartmentFilterRequest filter);
    void addDepartmentCriteria(DepartmentFilterRequest filter);
    void deleteDepartmentCriteria(DepartmentFilterRequest filter);
    void updateDepartmentCriteria(DepartmentFilterRequest filter);
}

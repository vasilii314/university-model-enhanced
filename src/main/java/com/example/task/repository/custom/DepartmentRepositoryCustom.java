package com.example.task.repository.custom;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<Department> findDepartmentsCriteria(DepartmentFilterRequest filter);
    void addDepartmentCriteria(DepartmentAddRequest filter);
    void deleteDepartmentCriteria(DepartmentFilterRequest filter);
    void updateDepartmentCriteria(DepartmentAddRequest filter);
}

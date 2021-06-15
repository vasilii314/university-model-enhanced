package com.example.task.service;

import com.example.task.entity.Department;

import java.util.List;

public interface DepartmentService extends Service<Department> {
    List<Department> findDepartmentsCriteria();
}

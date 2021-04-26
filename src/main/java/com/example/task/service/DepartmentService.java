package com.example.task.service;

import com.example.task.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(int id);
    void save(Department department);
    void deleteById(int id);
}

package com.example.task.repository;

import com.example.task.entity.Department;
import com.example.task.repository.custom.DepartmentRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends DepartmentRepositoryCustom, CrudRepository<Department, Integer> {
}

package com.example.task.service;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeAddRequest;
import com.example.task.json.filters.EmployeeFilterRequest;

import java.util.List;

public interface HumanService extends Service<Human> {
    List<Human> findEmployeesCriteria(EmployeeFilterRequest filter);
    void addEmployeeCriteria(EmployeeAddRequest filter);
}

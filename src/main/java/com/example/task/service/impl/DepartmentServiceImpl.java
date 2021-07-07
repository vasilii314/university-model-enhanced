package com.example.task.service.impl;

import com.example.task.entity.Department;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;
import com.example.task.json.responses.DepartmentDTO;
import com.example.task.repository.DepartmentRepository;
import com.example.task.repository.custom.DepartmentRepositoryCustom;
import com.example.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentRepositoryCustom departmentRepositoryCustom;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentRepositoryCustom departmentRepositoryCustom) {
        this.departmentRepository = departmentRepository;
        this.departmentRepositoryCustom = departmentRepositoryCustom;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentDTO> findDepartments(DepartmentFilterRequest filter) {
        return departmentRepositoryCustom
                .findDepartments(filter)
                .stream()
                .map(DepartmentDTO::toDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addDepartment(DepartmentAddRequest filter) {
        departmentRepositoryCustom.addDepartment(filter);
    }

    @Override
    public void deleteDepartment(DepartmentFilterRequest filter) {
        departmentRepositoryCustom.deleteDepartment(filter);
    }

    @Override
    public void updateDepartment(DepartmentAddRequest filter) {
        departmentRepositoryCustom.updateDepartment(filter);
    }
}

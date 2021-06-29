package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.exception.custom.DepartmentNotFoundException;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;
import com.example.task.repository.default_repos.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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
    public List<Department> findDepartmentsCriteria(DepartmentFilterRequest filter) {
        List<Department> departments = departmentRepository.findDepartmentsCriteria(filter);
        if (departments.size() == 0) {
            throw new DepartmentNotFoundException();
        }
        return departments;
    }

    @Override
    public void addDepartmentCriteria(DepartmentAddRequest filter) {
        departmentRepository.addDepartmentCriteria(filter);
    }

    @Override
    public void deleteDepartmentCriteria(DepartmentFilterRequest filter) {
        departmentRepository.deleteDepartmentCriteria(filter);
    }

    @Override
    public void updateDepartmentCriteria(DepartmentAddRequest filter) {
        departmentRepository.updateDepartmentCriteria(filter);
    }
}

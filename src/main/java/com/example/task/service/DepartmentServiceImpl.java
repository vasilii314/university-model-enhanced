package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.entity.School;
import com.example.task.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private EntityManager entityManager;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EntityManager entityManager) {
        this.departmentRepository = departmentRepository;
        this.entityManager = entityManager;
    }
    
    public List<Department> criteriaFindAll() {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<School> criteriaQuery = builder.createQuery(School.class);
    	Root<School> root = criteriaQuery.from(School.class);
    	criteriaQuery.select(root);
    	return null;
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
    public List<Department> findDepartmentsCriteria() {
        return null;
    }
}

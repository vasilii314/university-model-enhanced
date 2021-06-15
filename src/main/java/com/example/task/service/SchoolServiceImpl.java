package com.example.task.service;

import com.example.task.entity.School;
import com.example.task.repository.SchoolRepository;
import com.example.task.specification.SchoolSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;

    private EntityManager entityManager;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, EntityManager entityManager) {
        this.schoolRepository = schoolRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<School> findAllCriteria() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<School> criteriaQuery = builder.createQuery(School.class);
        Root<School> root = criteriaQuery.from(School.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<School> findAllWithConstraints(SchoolSpecification specification) {
        return schoolRepository.findAll(specification);
    }

    @Override
    public List<School> findAll() {
        List<School> schools = new ArrayList<>();
        schoolRepository.findAll().forEach(schools::add);
        return schools;
    }

    @Override
    public Optional<School> findById(int id) {
        return schoolRepository.findById(id);
    }

    @Override
    public void save(School school) {
        schoolRepository.save(school);
    }

    @Override
    public void deleteById(int id) {
        schoolRepository.deleteById(id);
    }
}

package com.example.task.service;

import com.example.task.entity.School;
import com.example.task.specification.SchoolSpecification;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    List<School> findAll();
    List<School> findAllCriteria();
    List<School> findAllWithConstraints(SchoolSpecification specification);
    Optional<School> findById(int id);
    void save(School school);
    void deleteById(int id);
}

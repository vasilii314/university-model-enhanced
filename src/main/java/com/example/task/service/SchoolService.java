package com.example.task.service;

import com.example.task.entity.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    List<School> findAll();
    Optional<School> findById(int id);
    void save(School school);
    void deleteById(int id);
}

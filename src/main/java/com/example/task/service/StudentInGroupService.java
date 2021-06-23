package com.example.task.service;

import com.example.task.entity.StudentsInGroups;

import java.util.List;
import java.util.Optional;

public interface StudentInGroupService {
    List<StudentsInGroups> findAll();
    Optional<StudentsInGroups> findById(int id);
    void save(StudentsInGroups student);
    void deleteById(int id);
}

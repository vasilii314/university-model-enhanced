package com.example.task.service;

import com.example.task.entity.StudentGrade;

import java.util.List;
import java.util.Optional;

public interface StudentGradeService {
    List<StudentGrade> findAll();
    Optional<StudentGrade> findById(int id);
    void save(StudentGrade grade);
    void deleteById(int id);
}

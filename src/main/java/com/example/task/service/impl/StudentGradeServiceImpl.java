package com.example.task.service.impl;

import com.example.task.entity.StudentGrade;
import com.example.task.repository.StudentGradesRepository;
import com.example.task.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {

    private final StudentGradesRepository studentGradesRepository;

    @Autowired
    public StudentGradeServiceImpl(StudentGradesRepository studentGradesRepository) {
        this.studentGradesRepository = studentGradesRepository;
    }

    @Override
    public List<StudentGrade> findAll() {
        List<StudentGrade> grades = new ArrayList<>();
        studentGradesRepository.findAll().forEach(grades::add);
        return grades;
    }

    @Override
    public Optional<StudentGrade> findById(int id) {
        return studentGradesRepository.findById(id);
    }

    @Override
    public void save(StudentGrade grade) {
        studentGradesRepository.save(grade);
    }

    @Override
    public void deleteById(int id) {
        studentGradesRepository.deleteById(id);
    }
}

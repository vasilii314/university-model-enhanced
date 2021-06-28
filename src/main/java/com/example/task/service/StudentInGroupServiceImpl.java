package com.example.task.service;

import com.example.task.entity.StudentsInGroups;
import com.example.task.repository.default_repos.StudentInGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentInGroupServiceImpl implements StudentInGroupService {

    private StudentInGroupRepository studentInGroupRepository;

    @Autowired
    public StudentInGroupServiceImpl(StudentInGroupRepository studentInGroupRepository) {
        this.studentInGroupRepository = studentInGroupRepository;
    }

    @Override
    public List<StudentsInGroups> findAll() {
        List<StudentsInGroups> students = new ArrayList<>();
        studentInGroupRepository.findAll().forEach(students::add);
        return students;
    }

    @Override
    public Optional<StudentsInGroups> findById(int id) {
        return studentInGroupRepository.findById(id);
    }

    @Override
    public void save(StudentsInGroups student) {
        studentInGroupRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentInGroupRepository.deleteById(id);
    }
}

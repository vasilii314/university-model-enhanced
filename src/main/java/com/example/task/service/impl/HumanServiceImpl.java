package com.example.task.service.impl;

import com.example.task.entity.*;
import com.example.task.exception.custom.StudentGradeNotFoundException;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
import com.example.task.json.responses.EmployeeDTO;
import com.example.task.json.responses.StudentDTO;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.repository.HumanRepository;
import com.example.task.repository.custom.HumanRepositoryCustom;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HumanServiceImpl implements HumanService {

    private final HumanRepository humanRepository;
    private final HumanRepositoryCustom humanRepositoryCustom;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository, HumanRepositoryCustom humanRepositoryCustom) {
        this.humanRepository = humanRepository;
        this.humanRepositoryCustom = humanRepositoryCustom;
    }

    @Override
    public List<Human> findAll() {
        return humanRepository.findAll();
    }

    @Override
    public Optional<Human> findById(int id) {
        return humanRepository.findById(id);
    }

    @Override
    public void save(Human human) {
        humanRepository.save(human);
    }

    @Override
    public void deleteById(int id) {
        humanRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> findStudents(StudentFilterRequest filter) {
        return humanRepositoryCustom
                .findStudents(filter)
                .stream()
                .map(StudentDTO::toStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addStudent(StudentAddRequest filter) {
        humanRepositoryCustom.addStudent(filter);
    }

    @Override
    public List<StudentGradeDTO> getStudentGrades(StudentFilterRequest filter) {
        List<StudentGradeDTO> studentGrades = humanRepositoryCustom.getStudentGrades(filter);
        if (studentGrades.size() == 0) {
            throw new StudentGradeNotFoundException();
        }
        return studentGrades;
    }

    @Override
    public void addStudentGrade(StudentAddRequest filter) {
        humanRepositoryCustom.addStudentGrade(filter);
    }

    @Override
    public List<EmployeeDTO> findEmployees(EmployeeFilterRequest filter) {
        return humanRepositoryCustom
                .findEmployees(filter)
                .stream()
                .map(EmployeeDTO::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(EmployeeAddRequest filter) {
        humanRepositoryCustom.addEmployee(filter);
    }

    @Override
    public void deleteEmployeeOrStudent(EmployeeFilterRequest filter) {
        humanRepositoryCustom.deleteEmployeeOrStudent(filter);
    }

    @Override
    public void updateEmployeeOrStudent(EmployeeAddRequest filter) {
        humanRepositoryCustom.updateEmployeeOrStudent(filter);
    }
}

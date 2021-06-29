package com.example.task.service;

import com.example.task.entity.*;
import com.example.task.exception.custom.EmployeeNotFoundException;
import com.example.task.exception.custom.StudentGradeNotFoundException;
import com.example.task.exception.custom.StudentNotFoundException;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.repository.default_repos.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HumanServiceImpl implements HumanService {

    private HumanRepository humanRepository;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public List<Human> findAll() {
        List<Human> people = new ArrayList<>();
        humanRepository.findAll().forEach(people::add);
        return people;
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
    public List<Human> findStudentsCriteria(StudentFilterRequest filter) {
        List<Human> students = humanRepository.findStudentsCriteria(filter);
        if (students.size() == 0) {
            throw new StudentNotFoundException();
        }
        return students;
    }

    @Override
    public void addStudentCriteria(StudentAddRequest filter) {
        humanRepository.addStudentCriteria(filter);
    }

    @Override
    public List<StudentGradeDTO> getStudentGradesCriteria(StudentFilterRequest filter) {
        List<StudentGradeDTO> studentGrades = humanRepository.getStudentGradesCriteria(filter);
        if (studentGrades.size() == 0) {
            throw new StudentGradeNotFoundException();
        }
        return studentGrades;
    }

    @Override
    public void addStudentGradeCriteria(StudentAddRequest filter) {
        humanRepository.addStudentGradeCriteria(filter);
    }

    @Override
    public List<Human> findEmployeesCriteria(EmployeeFilterRequest filter) {
        List<Human> employees = humanRepository.findEmployeesCriteria(filter);
        if (employees.size() == 0) {
            throw new EmployeeNotFoundException();
        }
        return employees;
    }

    @Override
    public void addEmployeeCriteria(EmployeeAddRequest filter) {
        humanRepository.addEmployeeCriteria(filter);
    }

    @Override
    public void deleteEmployeeOrStudentCriteria(EmployeeFilterRequest filter) {
        humanRepository.deleteEmployeeOrStudentCriteria(filter);
    }

    @Override
    public void updateEmployeeOrStudentCriteria(EmployeeAddRequest filter) {
        humanRepository.updateEmployeeOrStudentCriteria(filter);
    }
}

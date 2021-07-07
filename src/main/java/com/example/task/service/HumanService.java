package com.example.task.service;

import com.example.task.entity.Human;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
import com.example.task.json.responses.EmployeeDTO;
import com.example.task.json.responses.StudentDTO;
import com.example.task.json.responses.StudentGradeDTO;

import java.util.List;

public interface HumanService extends Service<Human> {
    List<EmployeeDTO> findEmployees(EmployeeFilterRequest filter);

    void addEmployee(EmployeeAddRequest filter);

    void deleteEmployeeOrStudent(EmployeeFilterRequest filter);

    void updateEmployeeOrStudent(EmployeeAddRequest filter);

    List<StudentDTO> findStudents(StudentFilterRequest filter);

    void addStudent(StudentAddRequest filter);

    List<StudentGradeDTO> getStudentGrades(StudentFilterRequest filter);

    void addStudentGrade(StudentAddRequest filter);
}

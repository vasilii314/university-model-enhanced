package com.example.task.service;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeAddRequest;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentGradeDTO;

import java.util.List;

public interface HumanService extends Service<Human> {
    List<Human> findEmployeesCriteria(EmployeeFilterRequest filter);
    void addEmployeeCriteria(EmployeeAddRequest filter);
    void deleteEmployeeOrStudent(EmployeeFilterRequest filter);
    void updateEmployeeOrStudent(EmployeeFilterRequest filter);
    List<Human> findStudents(StudentFilterRequest filter);
    void addStudentCriteria(StudentFilterRequest filter);
    List<StudentGradeDTO> getStudentGrades(StudentFilterRequest filter);
    void addStudentGrade(StudentFilterRequest filter);
}

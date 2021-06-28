package com.example.task.repository.custom;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeAddRequest;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.service.Service;

import java.util.List;

public interface HumanRepositoryCustom {
    List<Human> findEmployeesCriteria(EmployeeFilterRequest filter);
    void addEmployeeCriteria(EmployeeAddRequest filter);
    void deleteEmployeeOrStudentCriteria(EmployeeFilterRequest filter);
    void updateEmployeeOrStudentCriteria(EmployeeFilterRequest filter);
    List<Human> findStudentsCriteria(StudentFilterRequest filter);
    void addStudentCriteria(StudentFilterRequest filter);
    List<StudentGradeDTO> getStudentGradesCriteria(StudentFilterRequest filter);
    void addStudentGradeCriteria(StudentFilterRequest filter);
}

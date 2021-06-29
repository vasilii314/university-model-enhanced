package com.example.task.repository.custom;

import com.example.task.entity.Human;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
import com.example.task.json.responses.StudentGradeDTO;

import java.util.List;

public interface HumanRepositoryCustom {
    List<Human> findEmployeesCriteria(EmployeeFilterRequest filter);
    void addEmployeeCriteria(EmployeeAddRequest filter);
    void deleteEmployeeOrStudentCriteria(EmployeeFilterRequest filter);
    void updateEmployeeOrStudentCriteria(EmployeeAddRequest filter);
    List<Human> findStudentsCriteria(StudentFilterRequest filter);
    void addStudentCriteria(StudentAddRequest filter);
    List<StudentGradeDTO> getStudentGradesCriteria(StudentFilterRequest filter);
    void addStudentGradeCriteria(StudentAddRequest filter);
}

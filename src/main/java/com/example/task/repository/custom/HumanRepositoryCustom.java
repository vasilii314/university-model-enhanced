package com.example.task.repository.custom;

import com.example.task.entity.Human;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.requests.save_or_update.StudentAddRequest;
import com.example.task.json.responses.StudentGradeDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepositoryCustom {
    List<Human> findEmployees(EmployeeFilterRequest filter);

    void addEmployee(EmployeeAddRequest filter);

    void deleteEmployeeOrStudent(EmployeeFilterRequest filter);

    void updateEmployeeOrStudent(EmployeeAddRequest filter);

    List<Human> findStudents(StudentFilterRequest filter);

    void addStudent(StudentAddRequest filter);

    List<StudentGradeDTO> getStudentGrades(StudentFilterRequest filter);

    void addStudentGrade(StudentAddRequest filter);
}

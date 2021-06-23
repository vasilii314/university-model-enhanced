package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentDTO;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {

    private HumanService humanService;

    @Autowired
    public StudentController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/students")
    public List<StudentDTO> getStudents(@RequestBody @Valid StudentFilterRequest req) {
        List<Human> studentsRaw = humanService.findStudents(req);
        List<StudentDTO> students = studentsRaw.stream().map(StudentDTO::toStudentDTO).collect(Collectors.toList());
        return students;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody @Valid StudentFilterRequest req) {
        humanService.addStudentCriteria(req);
    }

    @GetMapping("/students/grades")
    public List<StudentGradeDTO> getGrades(@RequestBody @Valid StudentFilterRequest req) {
        return humanService.getStudentGrades(req);
    }

    @PostMapping("/students/grades")
    public void addGrade(@RequestBody @Valid StudentFilterRequest req) {
        humanService.addStudentGrade(req);
    }

    @DeleteMapping("/students")
    public void deleteStudent(@RequestBody @Valid EmployeeFilterRequest req) {
        humanService.deleteEmployeeOrStudent(req);
    }

    @PatchMapping("/students")
    public void updateStudent(@RequestBody @Valid EmployeeFilterRequest req) {
        humanService.updateEmployeeOrStudent(req);
    }
}

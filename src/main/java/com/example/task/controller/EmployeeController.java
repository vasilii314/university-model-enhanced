package com.example.task.controller;

import com.example.task.entity.Human;
import com.example.task.json.filters.EmployeeAddRequest;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.json.responses.EmployeeDTO;
import com.example.task.repository.HumanInUniversityRepository;
import com.example.task.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private HumanService humanService;
    private HumanInUniversityRepository humanInUniversityRepository;

    @Autowired
    public EmployeeController(HumanService humanService, HumanInUniversityRepository humanInUniversityRepository) {
        this.humanService = humanService;
        this.humanInUniversityRepository = humanInUniversityRepository;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees(@RequestBody EmployeeFilterRequest req) {

        List<Human> peopleInUniversity = humanService.findEmployeesCriteria(req);
        List<EmployeeDTO> employees =  peopleInUniversity
                .stream()
                .map(EmployeeDTO::toEmployeeDTO)
                .collect(Collectors.toList());
        return employees;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody EmployeeAddRequest req) {
        humanService.addEmployeeCriteria(req);
    }

    @DeleteMapping("/employees")
    public void deleteEmployee(@RequestBody EmployeeFilterRequest req) {
        humanService.deleteEmployeeOrStudent(req);
    }

    @PatchMapping("/employees")
    public void updateEmployee(@RequestBody EmployeeFilterRequest req) {
        humanService.updateEmployeeOrStudent(req);
    }
}

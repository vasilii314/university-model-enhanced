package com.example.task.controller;

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
        List<EmployeeDTO> employees =  humanService.findEmployeesCriteria(req)
                .stream()
                .map(EmployeeDTO::toEmployeeDTO)
                .collect(Collectors.toList());
        return employees;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody EmployeeAddRequest req) {
//        Human employee = new Human(req.getEmployeeFullName(), LocalDate.parse(req.getBd()));
//        HumanInUniversity humanInUniversity = new HumanInUniversity();
//        Role role = new Role(req.getRole());
//        role.setId(0);
//        humanInUniversity.setId(0);
//        humanInUniversity.setHuman(employee);
//        humanInUniversity.setRoles(new ArrayList<>());
//        humanInUniversity.getRoles().add(role);
//        employee.setRoles(new ArrayList<>());
//        employee.getRoles().add(humanInUniversity);
//        humanService.save(employee);
//        humanInUniversityRepository.save(humanInUniversity);
//        return employee.getFullName();
        humanService.addEmployeeCriteria(req);
    }
}

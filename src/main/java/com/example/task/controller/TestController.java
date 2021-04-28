package com.example.task.controller;

import com.example.task.criteria.SearchCriteria;
import com.example.task.entity.Department;
import com.example.task.entity.Human;
import com.example.task.entity.School;
import com.example.task.operations.SearchOperation;
import com.example.task.request.SchoolRequest;
import com.example.task.service.DepartmentServiceImpl;
import com.example.task.service.HumanService;
import com.example.task.service.SchoolServiceImpl;

import com.example.task.specification.HumanSpecification;
import com.example.task.specification.SchoolSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// This controller is only for testing purposes. Warning: It may seem bulky at first glance

@RestController
@RequestMapping("/api")
public class TestController {

    private HumanService humanService;
    private SchoolServiceImpl schoolService;
    
    private DepartmentServiceImpl dptService;

    @Autowired
    public TestController(HumanService humanService, SchoolServiceImpl schoolService, DepartmentServiceImpl dptService) {
        this.humanService = humanService;
        this.schoolService = schoolService;
        this.dptService = dptService;
    }

    @GetMapping("/schools")
    public String getDepartments() {
    	School school = schoolService.findAllCriteria().get(0);
    	List<Department> dpts = school.getDepartments();
    	return school.getId() + school.getName() + " " + dpts.get(0).getId() + dpts.get(0).getName();
    }

    @GetMapping("/criteria")
    public List<String> testCriteriaQuery() {
        return dptService.criteriaFind();
    }


    @GetMapping("/getschoolswithspec")
    public List<School> testSchoolSpec(@Valid @RequestBody SchoolRequest req) {
        SchoolSpecification spec = new SchoolSpecification();
        spec.add(new SearchCriteria("name", req.getName(), SearchOperation.MATCH));
        return schoolService.findAllWithConstraints(spec);
    }

    @GetMapping("/getpeoplewithspec")
    public List<Human> testPeopleSpec() throws Exception {
        HumanSpecification spec  = new HumanSpecification();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        spec.add(new SearchCriteria("fullName", "Cressida", SearchOperation.MATCH_START));
        List<Human> people = humanService.findAllWithConstraints(spec);
        System.out.println(people.get(0).getBirthDate());
               return people.stream()
                .filter(human -> human.getBirthDate().isAfter(LocalDate.parse("1986-01-01")))
                .collect(Collectors.toList());
    }

    @GetMapping("/criteriajoin")
    public List<Department> testCriteriaJoinQuery() {
        List<Department> dptWithSchool = dptService.criteriaJoinSchoolTable();
//        for (Department dpt:
//             dptWithSchool) {
//            dpt.getSchool().setDepartments(null);
//        }
        return dptWithSchool;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/add")
    public void addEmployee() {
        Human human = new Human("Cressida Wells", LocalDate.now());
        humanService.save(human);
    }

    // Этот метод лучше вынести в отдельный контроллер для работы с учебными заведениями
    @PostMapping("/addschool")
    public void addSchool() {
        School school = new School("Brisbane School Of Mathematics");
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            departments.add(new Department("department" + (i + 1), school));
        }
        school.setDepartments(departments);
        schoolService.save(school);
    }
}

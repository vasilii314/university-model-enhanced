package com.example.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
//
//    private HumanService humanService;
//    private SchoolServiceImpl schoolService;
//
//    private DepartmentServiceImpl dptService;
//
//
//    @Autowired
//    public TestController(HumanService humanService, SchoolServiceImpl schoolService, DepartmentServiceImpl dptService) {
//        this.humanService = humanService;
//        this.schoolService = schoolService;
//        this.dptService = dptService;
//    }
//
//    @GetMapping("/schools")
//    public String getDepartments() {
//    	School school = schoolService.criteriaFindAll().get(0);
//    	List<Department> dpts = school.getDepartments();
//    	return school.getId() + school.getName() + " " + dpts.get(0).getId() + dpts.get(0).getName();
//    }
//
//
//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Hello";
//    }
//
////    @PostMapping("/add")
////    public void addEmployee() {
////        Human human = new Human("Cressida Wells", new Date());
////        humanService.save(human);
////    }
//
//    // Этот метод лучше вынести в отдельный контроллер для работы с учебными заведениями
//    @PostMapping("/addschool")
//    public void addSchool() {
//        School school = new School("Brisbane School Of Mathematics");
//        List<Department> departments = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            departments.add(new Department("department" + (i + 1), school));
//        }
//        school.setDepartments(departments);
//        schoolService.save(school);
//    }
}

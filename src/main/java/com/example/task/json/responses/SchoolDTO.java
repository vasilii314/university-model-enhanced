package com.example.task.json.responses;

import com.example.task.entity.School;

import java.util.List;
import java.util.stream.Collectors;

public class SchoolDTO {

    private final String schoolName;

    private final List<DepartmentDTO> departments;

    public SchoolDTO(String schoolName, List<DepartmentDTO> departments) {
        this.schoolName = schoolName;
        this.departments = departments;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public List<DepartmentDTO> getDepartments() {
        return departments;
    }

    public static SchoolDTO toSchoolDTO(School school) {
        List<DepartmentDTO> departmentDTOS;
        departmentDTOS = school.getDepartments().stream().map(DepartmentDTO::toDepartmentDTO).collect(Collectors.toList());
        return new SchoolDTO(school.getName(), departmentDTOS);
    }
}

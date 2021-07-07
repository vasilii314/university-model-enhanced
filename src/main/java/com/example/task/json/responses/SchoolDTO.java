package com.example.task.json.responses;

import com.example.task.entity.School;
import org.hibernate.annotations.Immutable;

import java.util.List;
import java.util.stream.Collectors;

@Immutable
public class SchoolDTO {

    private final Integer id;
    private final String schoolName;

    private final List<DepartmentDTO> departments;

    public SchoolDTO(Integer id, String schoolName, List<DepartmentDTO> departments) {
        this.id = id;
        this.schoolName = schoolName;
        this.departments = departments;
    }

    public Integer getId() {
        return id;
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
        return new SchoolDTO(school.getId(), school.getName(), departmentDTOS);
    }
}

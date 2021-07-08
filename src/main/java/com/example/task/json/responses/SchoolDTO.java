package com.example.task.json.responses;

import org.hibernate.annotations.Immutable;

import java.util.List;

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
}

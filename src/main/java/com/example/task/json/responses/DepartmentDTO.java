package com.example.task.json.responses;

import org.hibernate.annotations.Immutable;

@Immutable
public class DepartmentDTO {

    private final Integer id;
    private final String departmentName;

    public DepartmentDTO(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

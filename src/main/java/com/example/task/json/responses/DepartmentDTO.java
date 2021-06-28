package com.example.task.json.responses;

import com.example.task.entity.Department;

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

    public static DepartmentDTO toDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName());
    }
}

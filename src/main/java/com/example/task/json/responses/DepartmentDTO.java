package com.example.task.json.responses;

import com.example.task.entity.Department;

public class DepartmentDTO {

    private String departmentName;

    public DepartmentDTO(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public static DepartmentDTO toDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getName());
    }
}

package com.example.task.json.responses;

import com.example.task.entity.Human;

public class EmployeeDTO {
    private final String fullName;

    public EmployeeDTO(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public static EmployeeDTO toEmployeeDTO(Human employee) {
        return new EmployeeDTO(employee.getFullName());
    }
}

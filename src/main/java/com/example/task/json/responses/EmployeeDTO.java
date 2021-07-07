package com.example.task.json.responses;

import com.example.task.entity.Human;
import org.hibernate.annotations.Immutable;

@Immutable
public class EmployeeDTO {

    private final Integer id;
    private final String fullName;

    public EmployeeDTO(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public static EmployeeDTO toEmployeeDTO(Human employee) {
        return new EmployeeDTO(employee.getId(), employee.getFullName());
    }
}



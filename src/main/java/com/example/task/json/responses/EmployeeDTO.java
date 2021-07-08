package com.example.task.json.responses;

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
}



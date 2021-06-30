package com.example.task.json.requests.save_or_update;

import com.example.task.entity.RoleEnum;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.updates.Updates;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeAddRequest {
    private final String employeeFullName;
    private final RoleEnum role;
    private final String birthDate;
    private final String dptName;
    private final String schoolName;
    private final Updates updates;
    private final EmployeeFilterRequest employeeFilter;

    public EmployeeAddRequest(@JsonProperty(value = "employeeFullName") String employeeFullName,
                              @JsonProperty(value = "role") RoleEnum role,
                              @JsonProperty(value = "birthDate") String birthDate,
                              @JsonProperty(value = "dptName") String dptName,
                              @JsonProperty(value = "schoolName") String schoolName,
                              @JsonProperty(value = "updates") Updates updates,
                              @JsonProperty(value = "employeeFilter") EmployeeFilterRequest employeeFilter) {
        this.employeeFullName = employeeFullName;
        this.role = role;
        this.birthDate = birthDate;
        this.dptName = dptName;
        this.schoolName = schoolName;
        this.updates = updates;
        this.employeeFilter = employeeFilter;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDptName() {
        return dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Updates getUpdates() {
        return updates;
    }

    public EmployeeFilterRequest getEmployeeFilter() {
        return employeeFilter;
    }
}

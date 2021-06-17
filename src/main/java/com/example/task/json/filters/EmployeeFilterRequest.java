package com.example.task.json.filters;

import com.example.task.entity.RoleEnum;
import com.example.task.json.updates.Updates;

public class EmployeeFilterRequest {
    private String employeeFullName;
    private String dptName;
    private RoleEnum role;
    private String birthDateUpperBound;
    private String birthDateLowerBound;
    private Updates updates;

    public EmployeeFilterRequest() {
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getBirthDateUpperBound() {
        return birthDateUpperBound;
    }

    public void setBirthDateUpperBound(String birthDateUpperBound) {
        this.birthDateUpperBound = birthDateUpperBound;
    }

    public String getBirthDateLowerBound() {
        return birthDateLowerBound;
    }

    public void setBirthDateLowerBound(String birthDateLowerBound) {
        this.birthDateLowerBound = birthDateLowerBound;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

package com.example.task.json.filters;

import com.example.task.entity.RoleEnum;
import com.example.task.json.updates.Updates;

import java.util.Map;

public class EmployeeFilterRequest {
    private String employeeFullName;
    private RoleEnum role;
    private Map<String, String> birthDate;
    private Updates updates;
    public EmployeeFilterRequest() {
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Map<String, String> getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Map<String, String> birthDate) {
        this.birthDate = birthDate;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

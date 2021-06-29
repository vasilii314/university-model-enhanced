package com.example.task.json.requests.save_or_update;

import com.example.task.entity.RoleEnum;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.updates.Updates;

public class EmployeeAddRequest {
    private String employeeFullName;
    private RoleEnum role;
    private String birthDate;
    private String dptName;
    private String schoolName;
    private Updates updates;
    private EmployeeFilterRequest employeeFilter;

    public EmployeeAddRequest() {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }

    public EmployeeFilterRequest getEmployeeFilter() {
        return employeeFilter;
    }

    public void setEmployeeFilter(EmployeeFilterRequest employeeFilter) {
        this.employeeFilter = employeeFilter;
    }
}

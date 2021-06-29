package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.updates.Updates;

public class DepartmentAddRequest {
    private String dptName;
    private String schoolName;
    private DepartmentFilterRequest departmentFilter;
    private Updates updates;

    public DepartmentAddRequest() {
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

    public DepartmentFilterRequest getDepartmentFilter() {
        return departmentFilter;
    }

    public void setDepartmentFilter(DepartmentFilterRequest departmentFilter) {
        this.departmentFilter = departmentFilter;
    }
}

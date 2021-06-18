package com.example.task.json.filters;

import com.example.task.json.updates.Updates;

public class DepartmentFilterRequest {
    private String dptName;
    private String schoolName;
    private Updates updates;

    public DepartmentFilterRequest() {
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
}

package com.example.task.json.requests.filters;

import com.example.task.json.updates.Updates;

import javax.validation.constraints.NotBlank;

public class SchoolFilterRequest {

    @NotBlank
    private String schoolName;

    private Updates updates;

    public SchoolFilterRequest() {
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}

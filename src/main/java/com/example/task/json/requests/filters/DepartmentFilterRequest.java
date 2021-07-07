package com.example.task.json.requests.filters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentFilterRequest {
    private final String dptName;
    private final String schoolName;

    public DepartmentFilterRequest(@JsonProperty(value = "dptName") String dptName,
                                   @JsonProperty(value = "schoolName") String schoolName) {
        this.dptName = dptName;
        this.schoolName = schoolName;
    }

    public String getDptName() {
        return dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
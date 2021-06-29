package com.example.task.json.requests.filters;

import javax.validation.constraints.NotBlank;

public class SchoolFilterRequest {

    @NotBlank
    private String schoolName;

    public SchoolFilterRequest() {
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "{" +
                "schoolName='" + schoolName + '\'' +
                '}';
    }
}

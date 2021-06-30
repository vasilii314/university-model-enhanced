package com.example.task.json.requests.filters;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SchoolFilterRequest {

    @NotBlank
    private final String schoolName;

    public SchoolFilterRequest(@JsonProperty(value = "schoolName") String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public String toString() {
        return "{" +
                "schoolName='" + schoolName + '\'' +
                '}';
    }
}

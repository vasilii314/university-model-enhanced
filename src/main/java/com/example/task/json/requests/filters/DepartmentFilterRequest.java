package com.example.task.json.requests.filters;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Immutable;

@Immutable
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
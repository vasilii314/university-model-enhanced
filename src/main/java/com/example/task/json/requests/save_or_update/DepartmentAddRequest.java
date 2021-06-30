package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.updates.Updates;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentAddRequest {
    private final String dptName;
    private final String schoolName;
    private final DepartmentFilterRequest departmentFilter;
    private final Updates updates;

    public DepartmentAddRequest(@JsonProperty(value = "dptName") String dptName,
                                @JsonProperty(value = "schoolName") String schoolName,
                                @JsonProperty(value = "departmentFilter") DepartmentFilterRequest departmentFilter,
                                @JsonProperty(value = "updates") Updates updates) {
        this.dptName = dptName;
        this.schoolName = schoolName;
        this.departmentFilter = departmentFilter;
        this.updates = updates;
    }

    public String getDptName() {
        return dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Updates getUpdates() {
        return updates;
    }

    public DepartmentFilterRequest getDepartmentFilter() {
        return departmentFilter;
    }
}

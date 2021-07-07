package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.updates.UpdatesForEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentAddRequest {
    private final String dptName;
    private final String schoolName;
    private final DepartmentFilterRequest departmentFilter;
    private final UpdatesForEntity updatesForEntity;

    public DepartmentAddRequest(@JsonProperty(value = "dptName") String dptName,
                                @JsonProperty(value = "schoolName") String schoolName,
                                @JsonProperty(value = "departmentFilter") DepartmentFilterRequest departmentFilter,
                                @JsonProperty(value = "updates") UpdatesForEntity updatesForEntity) {
        this.dptName = dptName;
        this.schoolName = schoolName;
        this.departmentFilter = departmentFilter;
        this.updatesForEntity = updatesForEntity;
    }

    public String getDptName() {
        return dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public UpdatesForEntity getUpdates() {
        return updatesForEntity;
    }

    public DepartmentFilterRequest getDepartmentFilter() {
        return departmentFilter;
    }
}

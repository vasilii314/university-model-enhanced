package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.updates.UpdatesForEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolAddRequest {
    private final String schoolName;
    private final SchoolFilterRequest schoolFilter;
    private final UpdatesForEntity updatesForEntity;

    public SchoolAddRequest(@JsonProperty(value = "schoolName") String schoolName,
                            @JsonProperty(value = "schoolFilter") SchoolFilterRequest schoolFilter,
                            @JsonProperty(value = "updates") UpdatesForEntity updatesForEntity) {
        this.schoolName = schoolName;
        this.schoolFilter = schoolFilter;
        this.updatesForEntity = updatesForEntity;
    }

    public String getSchoolName() {
        return schoolName;
    }


    public SchoolFilterRequest getSchoolFilter() {
        return schoolFilter;
    }

    public UpdatesForEntity getUpdates() {
        return updatesForEntity;
    }
}

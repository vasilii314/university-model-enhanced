package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.updates.Updates;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolAddRequest {
    private final String schoolName;
    private final SchoolFilterRequest schoolFilter;
    private final Updates updates;

    public SchoolAddRequest(@JsonProperty(value = "schoolName") String schoolName,
                            @JsonProperty(value = "schoolFilter") SchoolFilterRequest schoolFilter,
                            @JsonProperty(value = "updates") Updates updates) {
        this.schoolName = schoolName;
        this.schoolFilter = schoolFilter;
        this.updates = updates;
    }

    public String getSchoolName() {
        return schoolName;
    }


    public SchoolFilterRequest getSchoolFilter() {
        return schoolFilter;
    }

    public Updates getUpdates() {
        return updates;
    }
}

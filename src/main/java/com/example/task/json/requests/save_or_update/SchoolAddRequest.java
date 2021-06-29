package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.updates.Updates;

public class SchoolAddRequest {
    private String schoolName;
    private SchoolFilterRequest schoolFilter;
    private Updates updates;

    public SchoolAddRequest() {
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public SchoolFilterRequest getSchoolFilter() {
        return schoolFilter;
    }

    public void setSchoolFilter(SchoolFilterRequest schoolFilter) {
        this.schoolFilter = schoolFilter;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

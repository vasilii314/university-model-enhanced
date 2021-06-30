package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.updates.Updates;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentAddRequest {
    private final String studentFullName;
    private final String birthDate;
    private final StudentFilterRequest studentFilter;
    private final Updates updates;

    public StudentAddRequest(@JsonProperty(value = "studentFullName") String studentFullName,
                             @JsonProperty(value = "birthDate") String birthDate,
                             @JsonProperty(value = "studentFilter") StudentFilterRequest studentFilter,
                             @JsonProperty(value = "updates") Updates updates) {
        this.studentFullName = studentFullName;
        this.birthDate = birthDate;
        this.studentFilter = studentFilter;
        this.updates = updates;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public StudentFilterRequest getStudentFilter() {
        return studentFilter;
    }

    public Updates getUpdates() {
        return updates;
    }
}

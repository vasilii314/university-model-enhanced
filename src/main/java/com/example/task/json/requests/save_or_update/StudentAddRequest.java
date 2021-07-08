package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.updates.UpdatesForEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Immutable;

@Immutable
public class StudentAddRequest {
    private final String studentFullName;
    private final String birthDate;
    private final StudentFilterRequest studentFilter;
    private final UpdatesForEntity updatesForEntity;

    public StudentAddRequest(@JsonProperty(value = "studentFullName") String studentFullName,
                             @JsonProperty(value = "birthDate") String birthDate,
                             @JsonProperty(value = "studentFilter") StudentFilterRequest studentFilter,
                             @JsonProperty(value = "updates") UpdatesForEntity updatesForEntity) {
        this.studentFullName = studentFullName;
        this.birthDate = birthDate;
        this.studentFilter = studentFilter;
        this.updatesForEntity = updatesForEntity;
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

    public UpdatesForEntity getUpdates() {
        return updatesForEntity;
    }
}

package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.updates.Updates;

public class StudentAddRequest {
    private String studentFullName;
    private String birthDate;
    private StudentFilterRequest studentFilter;
    private Updates updates;

    public StudentAddRequest() {
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public StudentFilterRequest getStudentFilter() {
        return studentFilter;
    }

    public void setStudentFilter(StudentFilterRequest studentFilter) {
        this.studentFilter = studentFilter;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

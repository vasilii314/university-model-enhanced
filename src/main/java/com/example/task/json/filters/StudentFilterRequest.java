package com.example.task.json.filters;

import com.example.task.json.updates.Updates;

import javax.validation.constraints.Min;

public class StudentFilterRequest {
    private String studentFullName;
    private String birthDateUpperBound;
    private String birthDateLowerBound;
    private String birthDate;
    private String dptName;
    private String schoolName;
    private String courseName;
    private String groupName;

    @Min(0)
    private int gradeUpperBound;

    @Min(0)
    private int gradeLowerBound;

    private Updates updates;

    public StudentFilterRequest() {
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getBirthDateUpperBound() {
        return birthDateUpperBound;
    }

    public void setBirthDateUpperBound(String birthDateUpperBound) {
        this.birthDateUpperBound = birthDateUpperBound;
    }

    public String getBirthDateLowerBound() {
        return birthDateLowerBound;
    }

    public void setBirthDateLowerBound(String birthDateLowerBound) {
        this.birthDateLowerBound = birthDateLowerBound;
    }

    public int getGradeUpperBound() {
        return gradeUpperBound;
    }

    public void setGradeUpperBound(int gradeUpperBound) {
        this.gradeUpperBound = gradeUpperBound;
    }

    public int getGradeLowerBound() {
        return gradeLowerBound;
    }

    public void setGradeLowerBound(int gradeLowerBound) {
        this.gradeLowerBound = gradeLowerBound;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

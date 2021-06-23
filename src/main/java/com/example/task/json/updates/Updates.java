package com.example.task.json.updates;

import com.example.task.entity.CourseTypeEnum;
import com.example.task.entity.RoleEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class Updates {
    private String schoolName;
    private String dptName;
    private String groupName;
    private String courseName;
    private CourseTypeEnum courseType;
    private RoleEnum role;
    private String fullName;
    private LocalDate birthDate;

    @Positive
    @Min(2)
    @Max(5)
    private int grade;

    @Positive
    private Integer duration;

    public Updates() {
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseTypeEnum getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeEnum courseType) {
        this.courseType = courseType;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

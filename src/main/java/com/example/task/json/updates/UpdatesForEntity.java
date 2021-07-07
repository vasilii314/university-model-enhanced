package com.example.task.json.updates;

import com.example.task.entity.CourseTypeEnum;
import com.example.task.entity.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


/*
    This class represents a set of updates.
    Each field of this class is prefixed with respective entity name.
    Thus, some of these fields may be null. It depends on which
    entity will be updated.
*/

public class UpdatesForEntity {
    private final String schoolName;
    private final String dptName;
    private final String groupName;
    private final String courseName;
    private final CourseTypeEnum courseType;
    private final RoleEnum role;
    private final String fullName;
    private final LocalDate birthDate;

    @Positive
    @Min(2)
    @Max(5)
    private final Integer grade;

    @Positive
    private final Integer duration;

    public UpdatesForEntity(@JsonProperty(value = "schoolName") String schoolName,
                            @JsonProperty(value = "dptName") String dptName,
                            @JsonProperty(value = "groupName") String groupName,
                            @JsonProperty(value = "courseName") String courseName,
                            @JsonProperty(value = "courseType") CourseTypeEnum courseType,
                            @JsonProperty(value = "role") RoleEnum role,
                            @JsonProperty(value = "fullName") String fullName,
                            @JsonProperty(value = "birthDate") LocalDate birthDate,
                            @JsonProperty(value = "grade") Integer grade,
                            @JsonProperty(value = "duration") Integer duration) {
        this.schoolName = schoolName;
        this.dptName = dptName;
        this.groupName = groupName;
        this.courseName = courseName;
        this.courseType = courseType;
        this.role = role;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.grade = grade;
        this.duration = duration;
    }

    public String getSchoolName() {
        return schoolName;
    }

//    public void setSchoolName(String schoolName) {
//        this.schoolName = schoolName;
//    }

    public String getDptName() {
        return dptName;
    }

//    public void setDptName(String dptName) {
//        this.dptName = dptName;
//    }

    public String getGroupName() {
        return groupName;
    }

//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }

    public String getCourseName() {
        return courseName;
    }

//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }

    public CourseTypeEnum getCourseType() {
        return courseType;
    }

//    public void setCourseType(CourseTypeEnum courseType) {
//        this.courseType = courseType;
//    }

    public RoleEnum getRole() {
        return role;
    }

//    public void setRole(RoleEnum role) {
//        this.role = role;
//    }

    public String getFullName() {
        return fullName;
    }

//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }

    public Integer getDuration() {
        return duration;
    }

//    public void setDuration(Integer duration) {
//        this.duration = duration;
//    }

    public Integer getGrade() {
        return grade;
    }

//    public void setGrade(Integer grade) {
//        this.grade = grade;
//    }
}

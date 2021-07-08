package com.example.task.json.requests.filters;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Immutable;

import javax.validation.constraints.Min;

@Immutable
public class StudentFilterRequest {
    private final String studentFullName;
    private final String birthDateUpperBound;
    private final String birthDateLowerBound;
    private final String birthDate;
    private final String dptName;
    private final String schoolName;
    private final String courseName;
    private final String groupName;

    @Min(0)
    private final Integer gradeUpperBound;

    @Min(0)
    private final Integer gradeLowerBound;

    public StudentFilterRequest(@JsonProperty(value = "studentFullName") String studentFullName,
                                @JsonProperty(value = "birthDateUpperBound") String birthDateUpperBound,
                                @JsonProperty(value = "birthDateLowerBound") String birthDateLowerBound,
                                @JsonProperty(value = "birthDate") String birthDate,
                                @JsonProperty(value = "dptName") String dptName,
                                @JsonProperty(value = "schoolName") String schoolName,
                                @JsonProperty(value = "courseName") String courseName,
                                @JsonProperty(value = "groupName") String groupName,
                                @JsonProperty(value = "gradeUpperBound") Integer gradeUpperBound,
                                @JsonProperty(value = "gradeLowerBound") Integer gradeLowerBound) {
        this.studentFullName = studentFullName;
        this.birthDateUpperBound = birthDateUpperBound;
        this.birthDateLowerBound = birthDateLowerBound;
        this.birthDate = birthDate;
        this.dptName = dptName;
        this.schoolName = schoolName;
        this.courseName = courseName;
        this.groupName = groupName;
        this.gradeUpperBound = gradeUpperBound;
        this.gradeLowerBound = gradeLowerBound;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public String getBirthDateUpperBound() {
        return birthDateUpperBound;
    }

    public String getBirthDateLowerBound() {
        return birthDateLowerBound;
    }

    public Integer getGradeUpperBound() {
        return gradeUpperBound;
    }

    public Integer getGradeLowerBound() {
        return gradeLowerBound;
    }

    public String getDptName() {
        return dptName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getGroupName() {
        return groupName;
    }
}

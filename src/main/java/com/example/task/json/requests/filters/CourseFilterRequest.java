package com.example.task.json.requests.filters;

import com.example.task.entity.CourseTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Positive;

public class CourseFilterRequest {
    private final String courseName;
    private final String groupName;
    private final String dptName;
    private final CourseTypeEnum courseType;

    @Positive
    private final Integer durationUpperBound;

    @Positive
    private final Integer DurationLowerBound;

    @Positive
    private final Integer duration;

    public CourseFilterRequest(@JsonProperty(value = "courseName") String courseName,
                               @JsonProperty(value = "groupName") String groupName,
                               @JsonProperty(value = "dptName") String dptName,
                               @JsonProperty(value = "courseType") CourseTypeEnum courseType,
                               @JsonProperty(value = "durationUpperBound") Integer durationUpperBound,
                               @JsonProperty(value = "durationLowerBound") Integer durationLowerBound,
                               @JsonProperty(value = "duration") Integer duration) {
        this.courseName = courseName;
        this.groupName = groupName;
        this.dptName = dptName;
        this.courseType = courseType;
        this.durationUpperBound = durationUpperBound;
        DurationLowerBound = durationLowerBound;
        this.duration = duration;
    }

    public String getCourseName() {
        return courseName;
    }

//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }

    public String getGroupName() {
        return groupName;
    }

//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }

    public String getDptName() {
        return dptName;
    }

//    public void setDptName(String dptName) {
//        this.dptName = dptName;
//    }

    public CourseTypeEnum getCourseType() {
        return courseType;
    }

//    public void setCourseType(CourseTypeEnum courseType) {
//        this.courseType = courseType;
//    }

    public Integer getDurationUpperBound() {
        return durationUpperBound;
    }

//    public void setDurationUpperBound(Integer durationUpperBound) {
//        this.durationUpperBound = durationUpperBound;
//    }

    public Integer getDurationLowerBound() {
        return DurationLowerBound;
    }

//    public void setDurationLowerBound(Integer durationLowerBound) {
//        DurationLowerBound = durationLowerBound;
//    }

    public Integer getDuration() {
        return duration;
    }

//    public void setDuration(Integer duration) {
//        this.duration = duration;
//    }

    @Override
    public String toString() {
        return "{" +
                "courseName='" + courseName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", dptName='" + dptName + '\'' +
                ", courseType=" + courseType +
                ", durationUpperBound=" + durationUpperBound +
                ", DurationLowerBound=" + DurationLowerBound +
                ", duration=" + duration +
                '}';
    }
}

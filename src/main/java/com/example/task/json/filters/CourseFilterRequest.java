package com.example.task.json.filters;

import com.example.task.entity.CourseTypeEnum;
import com.example.task.json.updates.Updates;

import javax.validation.constraints.Positive;

public class CourseFilterRequest {
    private String courseName;
    private String groupName;
    private String dptName;
    private CourseTypeEnum courseType;
    private Updates updates;

    @Positive
    private Integer durationUpperBound;

    @Positive
    private Integer DurationLowerBound;

    @Positive
    private Integer duration;

    public CourseFilterRequest() {
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

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public CourseTypeEnum getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeEnum courseType) {
        this.courseType = courseType;
    }

    public Integer getDurationUpperBound() {
        return durationUpperBound;
    }

    public void setDurationUpperBound(Integer durationUpperBound) {
        this.durationUpperBound = durationUpperBound;
    }

    public Integer getDurationLowerBound() {
        return DurationLowerBound;
    }

    public void setDurationLowerBound(Integer durationLowerBound) {
        DurationLowerBound = durationLowerBound;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

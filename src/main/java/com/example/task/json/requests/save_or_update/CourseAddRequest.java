package com.example.task.json.requests.save_or_update;

import com.example.task.entity.CourseTypeEnum;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.updates.Updates;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Positive;

public class CourseAddRequest {
    private final String courseName;
    private final String groupName;
    private final String dptName;
    private final CourseTypeEnum courseType;
    private final CourseFilterRequest courseFilter;
    private final Updates updates;

    @Positive
    private final Integer durationUpperBound;

    @Positive
    private final Integer DurationLowerBound;

    @Positive
    private final Integer duration;

    public CourseAddRequest(@JsonProperty(value = "courseName") String courseName,
                            @JsonProperty(value = "groupName") String groupName,
                            @JsonProperty(value = "dptName") String dptName,
                            @JsonProperty(value = "courseType") CourseTypeEnum courseType,
                            @JsonProperty(value = "courseFilter") CourseFilterRequest courseFilter,
                            @JsonProperty(value = "updates") Updates updates,
                            @JsonProperty(value = "durationUpperBound") Integer durationUpperBound,
                            @JsonProperty(value = "Integer durationLowerBound") Integer durationLowerBound,
                            @JsonProperty(value = "duration") Integer duration) {
        this.courseName = courseName;
        this.groupName = groupName;
        this.dptName = dptName;
        this.courseType = courseType;
        this.courseFilter = courseFilter;
        this.updates = updates;
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

    public Updates getUpdates() {
        return updates;
    }

//    public void setUpdates(Updates updates) {
//        this.updates = updates;
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

    public CourseFilterRequest getCourseFilter() {
        return courseFilter;
    }

//    public void setCourseFilter(CourseFilterRequest courseFilter) {
//        this.courseFilter = courseFilter;
//    }
}

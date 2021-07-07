package com.example.task.json.requests.save_or_update;

import com.example.task.entity.CourseTypeEnum;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.updates.UpdatesForEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Positive;

public class CourseAddRequest {
    private final String courseName;
    private final String groupName;
    private final String dptName;
    private final CourseTypeEnum courseType;
    private final CourseFilterRequest courseFilter;
    private final UpdatesForEntity updatesForEntity;

    @Positive
    private final Integer durationUpperBound;

    @Positive
    private final Integer durationLowerBound;

    @Positive
    private final Integer duration;

    public CourseAddRequest(@JsonProperty(value = "courseName") String courseName,
                            @JsonProperty(value = "groupName") String groupName,
                            @JsonProperty(value = "dptName") String dptName,
                            @JsonProperty(value = "courseType") CourseTypeEnum courseType,
                            @JsonProperty(value = "courseFilter") CourseFilterRequest courseFilter,
                            @JsonProperty(value = "updates") UpdatesForEntity updatesForEntity,
                            @JsonProperty(value = "durationUpperBound") Integer durationUpperBound,
                            @JsonProperty(value = "Integer durationLowerBound") Integer durationLowerBound,
                            @JsonProperty(value = "duration") Integer duration) {
        this.courseName = courseName;
        this.groupName = groupName;
        this.dptName = dptName;
        this.courseType = courseType;
        this.courseFilter = courseFilter;
        this.updatesForEntity = updatesForEntity;
        this.durationUpperBound = durationUpperBound;
        this.durationLowerBound = durationLowerBound;
        this.duration = duration;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDptName() {
        return dptName;
    }

    public CourseTypeEnum getCourseType() {
        return courseType;
    }

    public UpdatesForEntity getUpdates() {
        return updatesForEntity;
    }

    public Integer getDurationUpperBound() {
        return durationUpperBound;
    }

    public Integer getDurationLowerBound() {
        return durationLowerBound;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseFilterRequest getCourseFilter() {
        return courseFilter;
    }
}

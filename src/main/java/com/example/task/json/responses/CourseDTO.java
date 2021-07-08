package com.example.task.json.responses;

import org.hibernate.annotations.Immutable;

@Immutable
public class CourseDTO {
    private final Integer id;
    private final String courseName;
    private final Integer duration;
    private final String dptName;

    public CourseDTO(Integer id, String courseName, Integer duration, String dptName) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
        this.dptName = dptName;
    }

    public Integer getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDptName() {
        return dptName;
    }
}

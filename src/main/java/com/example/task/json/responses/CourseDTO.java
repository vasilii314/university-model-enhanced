package com.example.task.json.responses;

public class CourseDTO {
    private final String courseName;
    private final Integer duration;
    private final String dptName;

    public CourseDTO(String courseName, Integer duration, String dptName) {
        this.courseName = courseName;
        this.duration = duration;
        this.dptName = dptName;
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

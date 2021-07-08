package com.example.task.json.responses;

import org.hibernate.annotations.Immutable;

@Immutable
public class StudentGradeDTO {

    private final Integer id;
    private final Integer grade;
    private final String studentName;
    private final String courseName;

    public StudentGradeDTO(Integer id, Integer grade, String studentName, String courseName) {
        this.id = id;
        this.grade = grade;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public Integer getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "StudentGradeDTO{" +
                "grade=" + grade +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}

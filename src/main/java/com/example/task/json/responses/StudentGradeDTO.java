package com.example.task.json.responses;


import com.example.task.entity.Course;
import com.example.task.entity.Human;
import com.example.task.entity.StudentGrade;

public class StudentGradeDTO {
    private int grade;
    private String studentName;
    private String courseName;

    public StudentGradeDTO() {
    }

    public StudentGradeDTO(int grade, String studentName, String courseName) {
        this.grade = grade;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public static StudentGradeDTO toStudentGradeDTO(Human student, StudentGrade grade, Course course) {
        return new StudentGradeDTO(grade.getGrade(), student.getFullName(), course.getName());
    }

    public static StudentGradeDTO toStudentGradeDTO(StudentGrade grade) {
        return new StudentGradeDTO(grade.getGrade(),
                grade.getStudent().getStudent().getHuman().getFullName(),
                grade.getCourse().getName());
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

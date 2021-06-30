package com.example.task.json.responses;


import com.example.task.entity.Course;
import com.example.task.entity.Human;
import com.example.task.entity.StudentGrade;

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

    public static StudentGradeDTO toStudentGradeDTO(Human student, StudentGrade grade, Course course) {
        return new StudentGradeDTO(grade.getId(), grade.getGrade(), student.getFullName(), course.getName());
    }

    public static StudentGradeDTO toStudentGradeDTO(StudentGrade grade) {
        return new StudentGradeDTO(grade.getId(), grade.getGrade(),
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

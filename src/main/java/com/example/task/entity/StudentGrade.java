package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "student_grades")
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private StudentsInGroups student;

    @Positive
    @Min(2)
    @Max(5)
    private Integer grade;

    public StudentGrade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudentsInGroups getStudent() {
        return student;
    }

    public void setStudent(StudentsInGroups student) {
        this.student = student;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "id=" + id +
                ", course=" + course +
                ", student=" + student +
                ", grade=" + grade +
                '}';
    }
}

package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(length = 100)
    private String name;

    @Positive
    private int duration;

    @ManyToOne
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

    @OneToMany(mappedBy = "course")
    private List<StudentGrade> grades;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public List<StudentGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<StudentGrade> grades) {
        this.grades = grades;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", courseType=" + courseType +
                ", department=" + department +
                '}';
    }
}

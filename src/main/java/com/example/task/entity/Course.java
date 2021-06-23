package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(length = 100)
    private String name;

    @Positive
    private int duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentGrade> grades;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    public Course() {
    }

    public Course(String name, int duration, CourseType courseType, Department department) {
        this.name = name;
        this.duration = duration;
        this.courseType = courseType;
        this.department = department;
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

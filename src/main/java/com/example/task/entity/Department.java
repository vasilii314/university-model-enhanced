package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<HumanInUniversity> people;

    @OneToMany(mappedBy = "groupDepartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Group> groups;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Course> courses;

    public Department() {
    }

    public Department(String name, School school) {
        this.name = name;
        this.school = school;
        this.people = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<HumanInUniversity> getPeople() {
        return people;
    }

    public void setPeople(List<HumanInUniversity> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", school=" + school +
                ", people=" + people+
                '}';
    }
}

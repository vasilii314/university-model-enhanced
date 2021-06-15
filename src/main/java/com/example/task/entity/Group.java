package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department groupDepartment;

    @OneToMany(mappedBy = "group")
    private List<StudentsInGroups> students;

    public Group() {
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

    public Department getDepartment() {
        return groupDepartment;
    }

    public void setDepartment(Department department) {
        this.groupDepartment = department;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + groupDepartment +
                '}';
    }
}

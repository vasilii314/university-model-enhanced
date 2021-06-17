package com.example.task.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "people_in_university")
public class HumanInUniversity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "human_id")
    private Human human;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentsInGroups> studentsInGroups;

    public HumanInUniversity() {
    }

    public HumanInUniversity(Human human, Role role, Department department) {
        this.human = human;
        this.role = role;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<StudentsInGroups> getStudentsInGroups() {
        return studentsInGroups;
    }

    public void setStudentsInGroups(List<StudentsInGroups> studentsInGroups) {
        this.studentsInGroups = studentsInGroups;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "HumanInUniversity{" +
                "id=" + id +
                ", human=" + human +
                ", role=" + role +
                ", department=" + department +
                ", studentsInGroups=" + studentsInGroups +
                '}';
    }
}

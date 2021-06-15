package com.example.task.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "people_in_university")
public class HumanInUniversity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "people_roles",
    joinColumns = {@JoinColumn(name = "human_in_uni_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "people_in_departments",
            joinColumns = {@JoinColumn(name = "human_in_uni_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    private List<Department> departments;

    @ManyToOne
    @JoinColumn(name = "human_id")
    private Human human;

    @OneToMany(mappedBy = "student")
    private List<StudentsInGroups> studentsInGroups;

    public HumanInUniversity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
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
                ", roles=" + roles +
                ", departments=" + departments +
                ", human=" + human +
                '}';
    }
}

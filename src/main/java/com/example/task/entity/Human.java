package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "people")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 100, min = 1)
    private String fullName;

    @NotNull
    private LocalDate birthDate;

    @OneToMany(mappedBy = "human", cascade = CascadeType.ALL)
    private List<HumanInUniversity> roles;

    public Human() {
    }

    public Human(String fullName, LocalDate birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<HumanInUniversity> getRoles() {
        return roles;
    }

    public void setRoles(List<HumanInUniversity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", roles=" + roles +
                '}';
    }
}

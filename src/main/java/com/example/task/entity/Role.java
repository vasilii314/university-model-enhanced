package com.example.task.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private RoleEnum roleDescription;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<HumanInUniversity> peopleWithRoles;

    public Role() {
    }

    public Role(RoleEnum roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleEnum getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(RoleEnum roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleDescription=" + roleDescription +
                '}';
    }
}

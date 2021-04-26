package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(int id);
    void save(Role role);
    void deleteById(int id);
}

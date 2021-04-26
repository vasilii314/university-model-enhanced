package com.example.task.service;

import com.example.task.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<Group> findAll();
    Optional<Group> findById(int id);
    void save(Group group);
    void deleteById(int id);
}

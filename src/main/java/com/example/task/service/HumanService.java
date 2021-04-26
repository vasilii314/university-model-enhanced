package com.example.task.service;

import com.example.task.entity.Human;

import java.util.List;
import java.util.Optional;

public interface HumanService {
    List<Human> findAll();
    Optional<Human> findById(int id);
    void save(Human human);
    void deleteById(int id);
}

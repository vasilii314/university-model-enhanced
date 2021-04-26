package com.example.task.service;

import com.example.task.entity.HumanInUniversity;

import java.util.List;
import java.util.Optional;

public interface HumanInUniversityService {
    List<HumanInUniversity> findAll();
    Optional<HumanInUniversity> findById(int id);
    void save(HumanInUniversity human);
    void deleteById(int id);
}

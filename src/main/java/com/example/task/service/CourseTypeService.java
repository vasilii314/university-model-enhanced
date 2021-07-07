package com.example.task.service;

import com.example.task.entity.CourseType;

import java.util.List;
import java.util.Optional;

public interface CourseTypeService {
    List<CourseType> findAll();

    Optional<CourseType> findById(int id);

    void save(CourseType courseType);

    void deleteById(int id);
}

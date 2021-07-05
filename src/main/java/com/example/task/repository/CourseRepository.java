package com.example.task.repository;

import com.example.task.entity.Course;
import com.example.task.repository.custom.CourseRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CourseRepositoryCustom, CrudRepository<Course, Integer> {
}

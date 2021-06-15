package com.example.task.repository;

import com.example.task.entity.CourseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepository extends CrudRepository<CourseType, Integer> {
}

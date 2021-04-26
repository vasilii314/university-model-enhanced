package com.example.task.repository;

import com.example.task.entity.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer> {
}

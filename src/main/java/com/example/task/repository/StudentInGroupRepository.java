package com.example.task.repository;

import com.example.task.entity.StudentsInGroups;
import org.springframework.data.repository.CrudRepository;

public interface StudentInGroupRepository extends CrudRepository<StudentsInGroups, Integer> {
}

package com.example.task.repository;

import com.example.task.entity.StudentsInGroups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInGroupRepository extends CrudRepository<StudentsInGroups, Integer> {
}

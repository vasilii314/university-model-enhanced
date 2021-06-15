package com.example.task.repository;

import com.example.task.entity.StudentGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGradesRepository extends CrudRepository<StudentGrade, Integer> {
}

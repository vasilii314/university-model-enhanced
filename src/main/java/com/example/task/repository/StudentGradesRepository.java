package com.example.task.repository;

import com.example.task.entity.StudentGrade;
import org.springframework.data.repository.CrudRepository;

public interface StudentGradesRepository extends CrudRepository<StudentGrade, Integer> {
}

package com.example.task.repository;

import com.example.task.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends CrudRepository<Human, Integer> {
}

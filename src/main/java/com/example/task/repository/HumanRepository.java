package com.example.task.repository;

import com.example.task.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Integer>, JpaSpecificationExecutor<Human> {
}

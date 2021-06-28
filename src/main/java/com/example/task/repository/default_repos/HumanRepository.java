package com.example.task.repository.default_repos;

import com.example.task.entity.Human;
import com.example.task.repository.custom.HumanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends HumanRepositoryCustom, JpaRepository<Human, Integer>, JpaSpecificationExecutor<Human> {
}

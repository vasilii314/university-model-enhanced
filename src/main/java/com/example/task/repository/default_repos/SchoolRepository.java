package com.example.task.repository.default_repos;

import com.example.task.entity.School;
import com.example.task.repository.custom.SchoolRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends SchoolRepositoryCustom, JpaRepository<School, Integer>, JpaSpecificationExecutor<School> {
}

package com.example.task.repository.default_repos;

import com.example.task.entity.HumanInUniversity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanInUniversityRepository extends CrudRepository<HumanInUniversity, Integer> {
}

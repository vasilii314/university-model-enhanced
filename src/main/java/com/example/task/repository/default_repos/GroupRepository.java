package com.example.task.repository.default_repos;

import com.example.task.entity.Group;
import com.example.task.repository.custom.GroupRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends GroupRepositoryCustom, CrudRepository<Group, Integer> {
}

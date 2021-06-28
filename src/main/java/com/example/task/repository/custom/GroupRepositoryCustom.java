package com.example.task.repository.custom;

import com.example.task.entity.Group;
import com.example.task.json.filters.GroupFilterRequest;
import com.example.task.service.Service;

import java.util.List;

public interface GroupRepositoryCustom {
    List<Group> findGroupsCriteria(GroupFilterRequest filter);
    void addGroupCriteria(GroupFilterRequest filter);
    void deleteGroupCriteria(GroupFilterRequest filter);
    void updateGroupCriteria(GroupFilterRequest filter);
}

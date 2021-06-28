package com.example.task.service;

import com.example.task.entity.Group;
import com.example.task.json.filters.GroupFilterRequest;

import java.util.List;

public interface GroupService extends Service<Group> {
    List<Group> findGroupsCriteria(GroupFilterRequest filter);
    void addGroupCriteria(GroupFilterRequest filter);
    void deleteGroupCriteria(GroupFilterRequest filter);
    void updateGroupCriteria(GroupFilterRequest filter);
}

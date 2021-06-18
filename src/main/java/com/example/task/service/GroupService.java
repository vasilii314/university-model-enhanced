package com.example.task.service;

import com.example.task.entity.Group;
import com.example.task.json.filters.GroupFilterRequest;

import java.util.List;

public interface GroupService extends Service<Group> {
    List<Group> findGroupsCriteria(GroupFilterRequest filter);
    void addGroup(GroupFilterRequest filter);
    void deleteGroup(GroupFilterRequest filter);
    void updateGroup(GroupFilterRequest filter);
}

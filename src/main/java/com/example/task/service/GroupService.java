package com.example.task.service;

import com.example.task.entity.Group;
import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.requests.save_or_update.GroupAddRequest;

import java.util.List;

public interface GroupService extends Service<Group> {
    List<Group> findGroupsCriteria(GroupFilterRequest filter);
    void addGroupCriteria(GroupAddRequest filter);
    void deleteGroupCriteria(GroupFilterRequest filter);
    void updateGroupCriteria(GroupAddRequest filter);
}

package com.example.task.repository.custom;

import com.example.task.entity.Group;
import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.requests.save_or_update.GroupAddRequest;

import java.util.List;

public interface GroupRepositoryCustom {
    List<Group> findGroups(GroupFilterRequest filter);
    void addGroup(GroupAddRequest filter);
    void deleteGroup(GroupFilterRequest filter);
    void updateGroup(GroupAddRequest filter);
}

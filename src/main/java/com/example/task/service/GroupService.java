package com.example.task.service;

import com.example.task.entity.Group;
import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.requests.save_or_update.GroupAddRequest;
import com.example.task.json.responses.GroupDTO;

import java.util.List;

public interface GroupService extends Service<Group> {
    List<GroupDTO> findGroups(GroupFilterRequest filter);

    void addGroup(GroupAddRequest filter);

    void deleteGroup(GroupFilterRequest filter);

    void updateGroup(GroupAddRequest filter);
}

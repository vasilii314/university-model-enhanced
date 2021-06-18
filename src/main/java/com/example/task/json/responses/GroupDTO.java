package com.example.task.json.responses;

import com.example.task.entity.Group;

public class GroupDTO {
    private final String groupName;

    public GroupDTO(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public static GroupDTO toGroupDTO(Group group) {
        return new GroupDTO(group.getName());
    }
}

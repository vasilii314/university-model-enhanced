package com.example.task.json.responses;

import com.example.task.entity.Group;

public class GroupDTO {
    private final Integer id;
    private final String groupName;
    private final String dptName;
    private final int numOfStudents;

    public GroupDTO(Integer id, String groupName, String dptName, int numOfStudents) {
        this.id = id;
        this.groupName = groupName;
        this.dptName = dptName;
        this.numOfStudents = numOfStudents;
    }

    public Integer getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public static GroupDTO toGroupDTO(Group group) {
        return new GroupDTO(group.getId(), group.getName(), group.getDepartment().getName(), group.getStudents().size());
    }

    public String getDptName() {
        return dptName;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }
}

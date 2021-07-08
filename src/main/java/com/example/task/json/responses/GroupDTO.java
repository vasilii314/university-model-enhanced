package com.example.task.json.responses;

import org.hibernate.annotations.Immutable;

@Immutable
public class GroupDTO {
    private final Integer id;
    private final String groupName;
    private final String dptName;
    private final Integer numOfStudents;

    public GroupDTO(Integer id, String groupName, String dptName, Integer numOfStudents) {
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

    public String getDptName() {
        return dptName;
    }

    public Integer getNumOfStudents() {
        return numOfStudents;
    }
}

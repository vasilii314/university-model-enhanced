package com.example.task.json.requests.filters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupFilterRequest {
    private final String groupName;
    private final String dptName;

    public GroupFilterRequest(@JsonProperty(value = "groupName") String groupName,
                              @JsonProperty(value = "dptName") String dptName) {
        this.groupName = groupName;
        this.dptName = dptName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDptName() {
        return dptName;
    }

}

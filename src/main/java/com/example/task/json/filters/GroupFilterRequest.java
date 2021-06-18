package com.example.task.json.filters;

import com.example.task.json.updates.Updates;

public class GroupFilterRequest {
    private String groupName;
    private String dptName;
    private Updates updates;

    public GroupFilterRequest() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

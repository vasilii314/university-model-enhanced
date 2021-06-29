package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.updates.Updates;

public class GroupAddRequest {
    private String groupName;
    private String dptName;
    private GroupFilterRequest groupFilter;
    private Updates updates;

    public GroupAddRequest() {
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

    public GroupFilterRequest getGroupFilter() {
        return groupFilter;
    }

    public void setGroupFilter(GroupFilterRequest groupFilter) {
        this.groupFilter = groupFilter;
    }

    public Updates getUpdates() {
        return updates;
    }

    public void setUpdates(Updates updates) {
        this.updates = updates;
    }
}

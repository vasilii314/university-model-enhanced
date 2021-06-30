package com.example.task.json.requests.save_or_update;

import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.updates.Updates;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupAddRequest {
    private final String groupName;
    private final String dptName;
    private final GroupFilterRequest groupFilter;
    private final Updates updates;

    public GroupAddRequest(@JsonProperty(value = "groupName") String groupName,
                           @JsonProperty(value = "dptName") String dptName,
                           @JsonProperty(value = "groupFilter") GroupFilterRequest groupFilter,
                           @JsonProperty(value = "updates") Updates updates) {
        this.groupName = groupName;
        this.dptName = dptName;
        this.groupFilter = groupFilter;
        this.updates = updates;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDptName() {
        return dptName;
    }

    public GroupFilterRequest getGroupFilter() {
        return groupFilter;
    }

    public Updates getUpdates() {
        return updates;
    }
}

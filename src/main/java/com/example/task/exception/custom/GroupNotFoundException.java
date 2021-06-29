package com.example.task.exception.custom;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Group not found");
    }
}

package com.example.task.exception.custom;

public class UpdatesObjectMissingException extends RuntimeException {
    public UpdatesObjectMissingException() {
        super("null might have been passed as an update");
    }
}

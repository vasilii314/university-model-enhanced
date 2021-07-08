package com.example.task.exception.custom;

public class DeleteOrUpdateException extends RuntimeException {
    public DeleteOrUpdateException() {
        super("Delete failed");
    }
}

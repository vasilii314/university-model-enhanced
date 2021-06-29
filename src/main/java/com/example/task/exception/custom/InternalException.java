package com.example.task.exception.custom;

public class InternalException extends RuntimeException {
    public InternalException() {
        super("An internal error has occurred");
    }
}

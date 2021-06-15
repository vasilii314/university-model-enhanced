package com.example.task.request;

import javax.validation.constraints.NotBlank;

public class SchoolRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SchoolRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}

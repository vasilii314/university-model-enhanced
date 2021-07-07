package com.example.task.json.requests.filters;

import com.example.task.entity.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Immutable;

@Immutable
public class EmployeeFilterRequest {
    private final String employeeFullName;
    private final String dptName;
    private final RoleEnum role;
    private final String birthDateUpperBound;
    private final String birthDateLowerBound;
    private final String groupName;

    public EmployeeFilterRequest(@JsonProperty(value = "employeeFullName") String employeeFullName,
                                 @JsonProperty(value = "dptName") String dptName,
                                 @JsonProperty(value = "role") RoleEnum role,
                                 @JsonProperty(value = "birthDateUpperBound") String birthDateUpperBound,
                                 @JsonProperty(value = "birthDateLowerBound") String birthDateLowerBound,
                                 @JsonProperty(value = "groupName") String groupName) {
        this.employeeFullName = employeeFullName;
        this.dptName = dptName;
        this.role = role;
        this.birthDateUpperBound = birthDateUpperBound;
        this.birthDateLowerBound = birthDateLowerBound;
        this.groupName = groupName;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public String getDptName() {
        return dptName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public String getBirthDateUpperBound() {
        return birthDateUpperBound;
    }

    public String getBirthDateLowerBound() {
        return birthDateLowerBound;
    }

    public String getGroupName() {
        return groupName;
    }
}

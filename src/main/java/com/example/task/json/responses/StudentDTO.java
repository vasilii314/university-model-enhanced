package com.example.task.json.responses;

import com.example.task.entity.Human;

public class StudentDTO {

    private final Integer id;
    private final String fullName;
    private final String birthDate;

    public StudentDTO(Integer id, String fullName, String birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public static StudentDTO toStudentDTO(Human student) {
        return new StudentDTO(student.getId(), student.getFullName(), student.getBirthDate().toString());
    }
}

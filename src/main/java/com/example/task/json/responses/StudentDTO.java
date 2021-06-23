package com.example.task.json.responses;

import com.example.task.entity.Human;

public class StudentDTO {
    private String fullName;
    private String birthDate;

    public StudentDTO() {
    }

    public StudentDTO(String fullName, String birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public static StudentDTO toStudentDTO(Human student) {
        return new StudentDTO(student.getFullName(), student.getBirthDate().toString());
    }
}

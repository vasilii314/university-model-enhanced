package com.example.task.json.mapper;

import com.example.task.entity.Human;
import com.example.task.json.responses.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentDtoMapper {
    StudentDtoMapper INSTANCE = Mappers.getMapper(StudentDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "student.id"),
            @Mapping(target = "fullName", source = "student.fullName"),
            @Mapping(target = "birthDate", source = "student.birthDate", dateFormat = "dd-MM-yyyy")
    })
    StudentDTO convert(Human student);
}

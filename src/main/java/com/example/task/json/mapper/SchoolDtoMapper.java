package com.example.task.json.mapper;

import com.example.task.entity.School;
import com.example.task.json.responses.SchoolDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DepartmentDtoMapper.class)
public interface SchoolDtoMapper {
    SchoolDtoMapper INSTANCE = Mappers.getMapper(SchoolDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "school.id"),
            @Mapping(target = "schoolName", source = "school.name"),
            @Mapping(target = "departments", source = "school.departments")
    })
    SchoolDTO convert(School school);
}

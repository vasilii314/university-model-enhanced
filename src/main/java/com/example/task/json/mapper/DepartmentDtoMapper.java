package com.example.task.json.mapper;

import com.example.task.entity.Department;
import com.example.task.json.responses.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentDtoMapper {
    DepartmentDtoMapper INSTANCE = Mappers.getMapper(DepartmentDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "department.id"),
            @Mapping(target = "departmentName", source = "department.name"),
    })
    DepartmentDTO convert(Department department);
}

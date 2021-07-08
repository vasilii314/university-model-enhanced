package com.example.task.json.mapper;

import com.example.task.entity.Human;
import com.example.task.json.responses.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeDtoMapper {
    EmployeeDtoMapper INSTANCE = Mappers.getMapper(EmployeeDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "employee.id"),
            @Mapping(target = "fullName", source = "employee.fullName"),
    })
    EmployeeDTO convert(Human employee);
}

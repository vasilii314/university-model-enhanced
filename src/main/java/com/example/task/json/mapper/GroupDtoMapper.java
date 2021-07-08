package com.example.task.json.mapper;

import com.example.task.entity.Group;
import com.example.task.json.responses.GroupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupDtoMapper {
    GroupDtoMapper INSTANCE = Mappers.getMapper(GroupDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "group.id"),
            @Mapping(target = "groupName", source = "group.name"),
            @Mapping(target = "dptName", source = "group.department.name"),
            @Mapping(target = "numOfStudents", expression = "java(group.getStudents() != null ? group.getStudents().size() : 0)")
    })
    GroupDTO convert(Group group);
}

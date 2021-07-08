package com.example.task.json.mapper;

import com.example.task.entity.Course;
import com.example.task.json.responses.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseDtoMapper {
    CourseDtoMapper INSTANCE = Mappers.getMapper(CourseDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "course.id"),
            @Mapping(target = "courseName", source = "course.name"),
            @Mapping(target = "duration", source = "course.duration"),
            @Mapping(target = "dptName", source = "course.department.name")
    })
    CourseDTO convert(Course course);
}

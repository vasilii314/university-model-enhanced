package com.example.task.json.mapper;

import com.example.task.entity.StudentGrade;
import com.example.task.json.responses.StudentGradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentGradeDtoMapper {
    StudentGradeDtoMapper INSTANCE = Mappers.getMapper(StudentGradeDtoMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "grade.id"),
            @Mapping(target = "grade", source = "grade.grade"),
            @Mapping(target = "studentName", source = "grade.student.student.human.fullName"),
            @Mapping(target = "courseName", source = "grade.course.name")
    })
    StudentGradeDTO convert(StudentGrade grade);
}

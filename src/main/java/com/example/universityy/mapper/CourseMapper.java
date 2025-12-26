package com.example.universityy.mapper;

import com.example.universityy.dto.CourseDto;
import com.example.universityy.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "title", source = "title")
    CourseDto toDto(Course course);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDto courseDto);

    List<CourseDto> toDtoList(List<Course> courses);
}
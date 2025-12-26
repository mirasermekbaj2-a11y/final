package com.example.universityy.mapper;

import com.example.universityy.dto.StudentDto;
import com.example.universityy.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "facultyId", source = "faculty.id")
    StudentDto toDto(Student student);

    @Mapping(target = "faculty", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Student toEntity(StudentDto studentDto);

    List<StudentDto> toDtoList(List<Student> students);
}
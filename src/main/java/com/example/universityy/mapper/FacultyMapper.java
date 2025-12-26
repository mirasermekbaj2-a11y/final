package com.example.universityy.mapper;

import com.example.universityy.dto.FacultyDto;
import com.example.universityy.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    @Mapping(target = "name", source = "name")
    FacultyDto toDto(Faculty faculty);

    @Mapping(target = "name", source = "name")
    Faculty toEntity(FacultyDto facultyDto);

    List<FacultyDto> toDtoList(List<Faculty> facultyList);

    List<Faculty> toEntityList(List<FacultyDto> facultyDtoList);
}
package com.example.universityy;

import com.example.universityy.dto.StudentDto;
import com.example.universityy.entity.Student;
import com.example.universityy.entity.Faculty;
import com.example.universityy.mapper.StudentMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void convertEntityToDtoTest() {
        Faculty faculty = new Faculty();
        faculty.setId(10L);
        faculty.setName("IT");

        Student entity = new Student();
        entity.setId(1L);
        entity.setName("Ivan");
        entity.setEmail("ivan@test.com");
        entity.setFaculty(faculty);

        StudentDto dto = studentMapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getFaculty().getId(), dto.getFacultyId());
    }

    @Test
    void convertDtoToEntityTest() {
        StudentDto dto = new StudentDto();
        dto.setId(1L);
        dto.setName("Ivan");
        dto.setEmail("ivan@test.com");

        Student entity = studentMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertNull(entity.getFaculty());
    }

    @Test
    void convertListEntityToListDtoTest() {
        List<Student> entities = new ArrayList<>();
        entities.add(new Student(1L, "Ivan", "ivan@test.com", null, null));
        entities.add(new Student(2L, "Petr", "petr@test.com", null, null));

        List<StudentDto> dtos = studentMapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(entities.size(), dtos.size());
        Assertions.assertEquals(entities.get(0).getName(), dtos.get(0).getName());
    }
}
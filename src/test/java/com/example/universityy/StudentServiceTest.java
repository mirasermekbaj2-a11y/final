package com.example.universityy;


import com.example.universityy.dto.FacultyDto;
import com.example.universityy.dto.StudentDto;
import com.example.universityy.service.FacultyService;
import com.example.universityy.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @Test
    void getAllStudentsTest() {
        List<StudentDto> students = studentService.getAllStudents();
        Assertions.assertNotNull(students);
    }

    @Test
    void addStudentTest() {
        FacultyDto faculty = new FacultyDto();
        faculty.setName("Test Faculty");
        FacultyDto savedFaculty = facultyService.create(faculty);

        StudentDto dto = new StudentDto();
        dto.setName("New Student");
        dto.setEmail("new@test.com");
        dto.setFacultyId(savedFaculty.getId());

        StudentDto created = studentService.createStudent(dto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getName(), created.getName());
        Assertions.assertEquals(savedFaculty.getId(), created.getFacultyId());
    }

    @Test
    void updateStudentTest() {
        List<StudentDto> all = studentService.getAllStudents();
        if (all.isEmpty()) return;

        Long id = all.get(0).getId();
        StudentDto updateDto = new StudentDto();
        updateDto.setName("Updated Name");
        updateDto.setEmail("updated@test.com");

        StudentDto result = studentService.updateStudent(id, updateDto);

        Assertions.assertEquals("Updated Name", result.getName());

        StudentDto check = studentService.getStudentById(id);
        Assertions.assertEquals("Updated Name", check.getName());
    }

    @Test
    void deleteStudentTest() {
        StudentDto dto = new StudentDto();
        dto.setName("To Delete");
        dto.setEmail("del@test.com");
        StudentDto saved = studentService.createStudent(dto);

        Long id = saved.getId();
        studentService.deleteStudent(id);

        Assertions.assertThrows(RuntimeException.class, () -> {
            studentService.getStudentById(id);
        });
    }
}
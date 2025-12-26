package com.example.universityy.service;


import com.example.universityy.dto.StudentDto;
import com.example.universityy.entity.Course;
import com.example.universityy.entity.Faculty;
import com.example.universityy.entity.Student;
import com.example.universityy.mapper.StudentMapper;
import com.example.universityy.repository.CourseRepository;
import com.example.universityy.repository.FacultyRepository;
import com.example.universityy.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    public StudentDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        if (studentDto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(studentDto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            student.setFaculty(faculty);
        }
        return studentMapper.toDto(studentRepository.save(student));
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDto(student);
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());

        if (studentDto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(studentDto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            student.setFaculty(faculty);
        }

        return studentMapper.toDto(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDto enrollStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        return studentMapper.toDto(studentRepository.save(student));
    }
}
package com.example.universityy.service;

import com.example.universityy.dto.CourseDto;
import com.example.universityy.entity.Course;
import com.example.universityy.mapper.CourseMapper;
import com.example.universityy.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseDto create(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);
        return courseMapper.toDto(courseRepository.save(course));
    }

    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toDto(course);
    }

    public CourseDto update(Long id, CourseDto dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(dto.getTitle());
        return courseMapper.toDto(courseRepository.save(course));
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}

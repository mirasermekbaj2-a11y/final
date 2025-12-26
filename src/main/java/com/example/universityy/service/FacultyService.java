package com.example.universityy.service;


import com.example.universityy.dto.FacultyDto;
import com.example.universityy.entity.Faculty;
import com.example.universityy.mapper.FacultyMapper;
import com.example.universityy.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    public FacultyDto create(FacultyDto dto) {
        Faculty faculty = facultyMapper.toEntity(dto);
        return facultyMapper.toDto(facultyRepository.save(faculty));
    }

    public List<FacultyDto> getAll() {
        return facultyRepository.findAll().stream()
                .map(facultyMapper::toDto)
                .collect(Collectors.toList());
    }

    public FacultyDto getById(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        return facultyMapper.toDto(faculty);
    }

    public FacultyDto update(Long id, FacultyDto dto) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        faculty.setName(dto.getName());
        return facultyMapper.toDto(facultyRepository.save(faculty));
    }

    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }
}
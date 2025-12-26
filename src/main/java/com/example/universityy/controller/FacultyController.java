package com.example.universityy.controller;

import com.example.universityy.dto.FacultyDto;
import com.example.universityy.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    public FacultyDto create(@RequestBody FacultyDto dto) {
        return facultyService.create(dto);
    }

    @GetMapping
    public List<FacultyDto> getAll() {
        return facultyService.getAll();
    }

    @GetMapping("/{id}")
    public FacultyDto getById(@PathVariable Long id) {
        return facultyService.getById(id);
    }

    @PutMapping("/{id}")
    public FacultyDto update(@PathVariable Long id, @RequestBody FacultyDto dto) {
        return facultyService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facultyService.delete(id);
    }
}
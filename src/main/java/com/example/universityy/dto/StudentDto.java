package com.example.universityy.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private Long facultyId;
}
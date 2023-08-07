package com.example.demo.dtos.responses;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassResponseDto {
    String name;
    TeacherResponse teacher;
    List<StudentResponse> students;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
class TeacherResponse{
    String name;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
class StudentResponse{
    String name;
}

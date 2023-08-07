package com.example.demo.dtos.requests;

import java.util.List;

import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassCreateRequestDto {
    String name;
    @JsonProperty("teacher-id")
    String teacherId;
    @JsonProperty("student-list-ids")
    List<String> studentIds;

    @JsonIgnore
    Teacher teacher;
    @JsonIgnore
    List<Student> students;
}

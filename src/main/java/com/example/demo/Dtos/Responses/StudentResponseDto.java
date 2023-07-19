package com.example.demo.dtos.responses;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Student;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
public class StudentResponseDto implements IDto<Student>{

    @JsonProperty("student-name")
    String name;

    @Override
    public Student toEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

    @Override
    public void toDto(Student entity) {
       BeanUtils.copyProperties(entity, this, "id");
    }
}

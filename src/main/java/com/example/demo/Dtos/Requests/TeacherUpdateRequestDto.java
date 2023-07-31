package com.example.demo.dtos.requests;

import java.util.*;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.*;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherUpdateRequestDto implements IDto<Teacher> {
    @JsonProperty("id")
    UUID id;

    @JsonProperty("name")
    String name;

    @Override
    public Teacher toEntity() {
        Teacher entity = new Teacher();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public void toDto(Teacher entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
}
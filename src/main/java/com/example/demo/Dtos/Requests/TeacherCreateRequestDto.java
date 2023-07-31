package com.example.demo.dtos.requests;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Teacher;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherCreateRequestDto implements IDto<Teacher> {
    
    @JsonProperty("name")
    String name;

    @Override
    public Teacher toEntity() {
        Teacher entity = new Teacher();
        BeanUtils.copyProperties(this, entity, "id","classes");
        return entity;
    }

    @Override
    public void toDto(Teacher entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }

}

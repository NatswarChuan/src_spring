package com.example.demo.dtos.requests;

import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.demo.entities.Class;

import java.util.*;

import org.springframework.beans.BeanUtils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassUpdateRequestDto extends ClassCreateRequestDto implements IDto<Class>{
    @JsonProperty("id")
    UUID id;

    @Override
    public Class toEntity() {
        Class entity = new Class();
        BeanUtils.copyProperties(this, entity, "teacherId");
        return entity;
    }
}

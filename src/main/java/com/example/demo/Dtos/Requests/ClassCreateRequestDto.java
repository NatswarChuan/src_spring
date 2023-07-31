package com.example.demo.dtos.requests;

import lombok.*;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Class;
import com.example.demo.entities.Teacher;
import com.example.demo.entities.Student;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassCreateRequestDto implements IDto<Class> {
    @JsonProperty("name")
    String name;

    @JsonProperty("teacher-id")
    UUID teacherId;
    
    @JsonProperty("list-student-id")
    List<UUID> studentIds;

    @JsonIgnore
    Teacher teacher;

    @JsonIgnore
    List<Student> students;

    @Override
    public Class toEntity() {
        Class entity = new Class();
        BeanUtils.copyProperties(this, entity, "id","teacherId");
        return entity;
    }

    @Override
    public void toDto(Class entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }

}

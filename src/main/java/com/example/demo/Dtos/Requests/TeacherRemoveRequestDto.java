package com.example.demo.dtos.requests;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Teacher;
import com.example.demo.interfaces.IDto;

import lombok.*;

@Getter
@Setter
@ToString
public class TeacherRemoveRequestDto extends RemoveByIdRequestDto implements IDto<Teacher> {
    @Override
    public Teacher toEntity() {
        Teacher entity = new Teacher();
        BeanUtils.copyProperties(this, entity,"name","classes");
        return entity;
    }

    @Override
    public void toDto(Teacher entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }

}

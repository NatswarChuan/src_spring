package com.example.demo.dtos.requests;


import org.springframework.beans.BeanUtils;

import com.example.demo.interfaces.IDto;
import com.example.demo.entities.Class;

import lombok.*;

@Getter
@Setter
@ToString
public class ClassRemoveRequestDto extends RemoveByIdRequestDto implements IDto<Class> {
    
    @Override
    public Class toEntity() {
        Class entity = new Class();
        BeanUtils.copyProperties(this, entity, "name", "teacher", "students");
        return entity;
    }

    @Override
    public void toDto(Class entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
}

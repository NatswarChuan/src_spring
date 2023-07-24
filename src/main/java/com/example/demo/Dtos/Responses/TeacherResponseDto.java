package com.example.demo.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.*;
import com.example.demo.entities.Class;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherResponseDto implements IDto<Teacher> {
    @JsonProperty("teacher-name")
    private String name;

    @JsonProperty("class-list")
    private List<CLassTempResponse> classList;

    @Override
    public Teacher toEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

    @Override
    public void toDto(Teacher entity) {
         BeanUtils.copyProperties(entity, this, "id", "classes");
        this.classList = new ArrayList<>();
        for (Class cLass : entity.getClasses()) {
            CLassTempResponse _class = new CLassTempResponse();
            BeanUtils.copyProperties( cLass,_class, "id", "teacher");
            this.classList.add( _class);
        }
    }
}

@Data
class CLassTempResponse{
    @JsonProperty("class-name")
    private String name;
}
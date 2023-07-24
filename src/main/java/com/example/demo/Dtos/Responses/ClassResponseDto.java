package com.example.demo.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Class;
import com.example.demo.entities.Student;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ClassResponseDto implements IDto<Class> {
    @JsonProperty("class-name")
    private String name;

    @JsonProperty("teacher")
    private TeacherTempResponse teacher;

    @JsonProperty("class-list")
    private List<StudentTempResponse> studentList;

    @Override
    public Class toEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

    @Override
    public void toDto(Class entity) {
        BeanUtils.copyProperties(entity, this, "id", "teacher", "students");
        this.teacher = new TeacherTempResponse();
        BeanUtils.copyProperties(entity.getTeacher(), this.teacher, "id", "classes");
        this.studentList = new ArrayList<>();
        for (Student student : entity.getStudents()) {
            StudentTempResponse _student = new StudentTempResponse();
            BeanUtils.copyProperties(student, _student, "id", "classes");
            this.studentList.add(_student);
        }
    }
}

@Data
class TeacherTempResponse {
    @JsonProperty("teacher-name")
    private String name;
}

@Data
class StudentTempResponse {
    @JsonProperty("class-name")
    private String name;
}

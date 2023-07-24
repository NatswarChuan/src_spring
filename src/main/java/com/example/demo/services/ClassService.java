package com.example.demo.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.commond.HttpException;
import com.example.demo.dtos.responses.TeacherResponseDto;
import com.example.demo.entities.Class;
import com.example.demo.entities.Teacher;
import com.example.demo.interfaces.services.IClassService;
import com.example.demo.repositories.ClassRepository;
import com.example.demo.repositories.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClassService extends AbService<Class, UUID> implements IClassService {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public TeacherResponseDto findTeacherById(UUID id) throws HttpException {
        TeacherResponseDto result = new TeacherResponseDto();
        Optional<Teacher> opnTeacher = teacherRepository.findByClasses_Id(id);
        if (opnTeacher.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST,"Không tìm thấy teacher");
        }
        Teacher teacher = opnTeacher.get();
        result.toDto(teacher);
        return result;
    }
}

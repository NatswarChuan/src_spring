package com.example.demo.interfaces.services;

import java.util.UUID;

import com.example.demo.commond.HttpException;
import com.example.demo.dtos.responses.TeacherResponseDto;
import com.example.demo.entities.Class;
import com.example.demo.interfaces.IService;

public interface IClassService extends IService<Class,UUID> {
    TeacherResponseDto findTeacherById(UUID id)  throws HttpException;
}

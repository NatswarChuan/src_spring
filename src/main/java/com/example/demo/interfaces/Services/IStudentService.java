package com.example.demo.interfaces.services;

import java.util.*;

import com.example.demo.commond.HttpException;
import com.example.demo.entities.Student;
import com.example.demo.interfaces.IService;

public interface IStudentService extends IService<Student,UUID> {
        List<Student> findStudents(List<UUID> ids)  throws HttpException;
}

package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.commond.HttpException;
import com.example.demo.entities.Student;
import com.example.demo.interfaces.services.IStudentService;
import com.example.demo.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService extends AbService<Student, UUID> implements IStudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findStudents(List<UUID> ids) throws HttpException {
        return this.studentRepository.findByIdIn(ids);
    }
}

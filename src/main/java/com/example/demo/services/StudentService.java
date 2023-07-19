package com.example.demo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Student;
import com.example.demo.interfaces.Services.IStudentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService extends AbService<Student, UUID> implements IStudentService {
}

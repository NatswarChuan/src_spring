package com.example.demo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Teacher;
import com.example.demo.interfaces.services.ITeacherService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeacherService extends AbService<Teacher, UUID> implements ITeacherService {
}

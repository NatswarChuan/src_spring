package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.commond.HttpException;
import com.example.demo.dtos.requests.ClassCreateRequestDto;
import com.example.demo.dtos.responses.ClassResponseDto;
import com.example.demo.entities.Class;
import com.example.demo.repositories.ClassRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.services.ClassService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassService classService;

    @GetMapping()
    public ResponseEntity<?> findAll()  throws HttpException{
      return new ResponseEntity(classService.findAll(ClassResponseDto.class),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable String id) throws HttpException {
       return new ResponseEntity(classService.findById(id,ClassResponseDto.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClassCreateRequestDto requestDto) throws HttpException {
      requestDto.setStudents(studentRepository.findByIdIn(requestDto.getStudentIds()));
      requestDto.setTeacher(teacherRepository.findById(requestDto.getTeacherId()).get());
      classService.create(requestDto, Class.class);
       return new ResponseEntity(HttpStatus.OK);
    }

}

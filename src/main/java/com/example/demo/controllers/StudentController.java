package com.example.demo.controllers;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.dtos.responses.StudentResponseDto;
import com.example.demo.interfaces.services.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    IStudentService studentService;

    @GetMapping({"/",""})
    public ResponseEntity<ResponseData<List<StudentResponseDto>>> findAll() throws HttpException {
        List<StudentResponseDto> data = studentService.findAll(StudentResponseDto.class);
        ResponseData<List<StudentResponseDto>> responseData = new ResponseData<>(HttpStatus.OK,"List of student",data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({"/{id}","/{id}/"})
    public ResponseEntity<ResponseData<StudentResponseDto>> findById(@PathVariable UUID id ) throws HttpException {
        StudentResponseDto data = studentService.findById(id,StudentResponseDto.class);
        ResponseData<StudentResponseDto> responseData = new ResponseData<>(HttpStatus.OK,"Student id="+id.toString(),data);
        return ResponseEntity.ok(responseData);
    }
}

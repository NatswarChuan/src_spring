package com.example.demo.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.dtos.responses.TeacherResponseDto;
import com.example.demo.interfaces.services.ITeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    ITeacherService teacherService;

    @GetMapping({"/",""})
    public ResponseEntity<ResponseData<List<TeacherResponseDto>>> findAll() throws HttpException {
        List<TeacherResponseDto> data = teacherService.findAll(TeacherResponseDto.class);
        ResponseData<List<TeacherResponseDto>> responseData = new ResponseData<>(HttpStatus.OK,"All data of class",data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({"/{id}","/{id}/"})
    public ResponseEntity<ResponseData<TeacherResponseDto>> findById(@PathVariable UUID id ) throws HttpException {
        TeacherResponseDto data = teacherService.findById(id,TeacherResponseDto.class);
        ResponseData<TeacherResponseDto> responseData = new ResponseData<>(HttpStatus.OK,"Student id="+id.toString(),data);
        return ResponseEntity.ok(responseData);
    }
}

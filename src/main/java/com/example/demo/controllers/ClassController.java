package com.example.demo.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.dtos.responses.ClassResponseDto;
import com.example.demo.dtos.responses.TeacherResponseDto;
import com.example.demo.interfaces.services.IClassService;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    IClassService classService;

    @GetMapping({"/",""})
    public ResponseEntity<ResponseData<List<ClassResponseDto>>> findAll() throws HttpException {
        List<ClassResponseDto> data = classService.findAll(ClassResponseDto.class);
        ResponseData<List<ClassResponseDto>> responseData = new ResponseData<>(HttpStatus.OK,"All data of class",data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({"/{id}","/{id}/"})
    public ResponseEntity<ResponseData<ClassResponseDto>> findById(@PathVariable UUID id ) throws HttpException {
        ClassResponseDto data = classService.findById(id,ClassResponseDto.class);
        ResponseData<ClassResponseDto> responseData = new ResponseData<>(HttpStatus.OK,"Teacher id="+id.toString(),data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({"/teacher/{id}","/teacher/{id}/"})
    public ResponseEntity<ResponseData<TeacherResponseDto>> findTeacherById(@PathVariable UUID id ) throws HttpException {
        TeacherResponseDto data = classService.findTeacherById(id);
        ResponseData<TeacherResponseDto> responseData = new ResponseData<>(HttpStatus.OK,"Teacher id="+id.toString(),data);
        return ResponseEntity.ok(responseData);
    }
}

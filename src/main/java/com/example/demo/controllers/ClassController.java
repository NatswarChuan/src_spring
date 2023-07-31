package com.example.demo.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.dtos.requests.ClassCreateRequestDto;
import com.example.demo.dtos.requests.ClassRemoveRequestDto;
import com.example.demo.dtos.requests.ClassUpdateRequestDto;
import com.example.demo.dtos.responses.ClassResponseDto;
import com.example.demo.dtos.responses.TeacherResponseDto;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.interfaces.services.IClassService;
import com.example.demo.interfaces.services.IStudentService;
import com.example.demo.interfaces.services.ITeacherService;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    IClassService classService;

    @Autowired
    ITeacherService teacherService;

    @Autowired
    IStudentService studentService;

    @GetMapping({ "/", "" })
    public ResponseEntity<ResponseData<List<ClassResponseDto>>> findAll() throws HttpException {
        List<ClassResponseDto> data = classService.findAll(ClassResponseDto.class);
        ResponseData<List<ClassResponseDto>> responseData = new ResponseData<>(HttpStatus.OK, "All data of class",
                data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity<ResponseData<ClassResponseDto>> findById(@PathVariable UUID id) throws HttpException {
        ClassResponseDto data = classService.findById(id, ClassResponseDto.class);
        ResponseData<ClassResponseDto> responseData = new ResponseData<>(HttpStatus.OK, "Class id=" + id.toString(),
                data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "/teacher/{id}", "/teacher/{id}/" })
    public ResponseEntity<ResponseData<TeacherResponseDto>> findTeacherById(@PathVariable UUID id)
            throws HttpException {
        TeacherResponseDto data = classService.findTeacherById(id);
        ResponseData<TeacherResponseDto> responseData = new ResponseData<>(HttpStatus.OK, "Teacher id=" + id.toString(),
                data);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "/create", "/create/" })
    public ResponseEntity<ResponseData<?>> create(@RequestBody ClassCreateRequestDto classCreateRequestDto)
            throws HttpException {
        Teacher teacher = teacherService.findById(classCreateRequestDto.getTeacherId());
        classCreateRequestDto.setTeacher(teacher);
        List<Student> students = studentService.findStudents(classCreateRequestDto.getStudentIds());
        classCreateRequestDto.setStudents(students);
        classService.create(classCreateRequestDto);
        
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "Class is created", null);
        return new ResponseEntity<ResponseData<?>>(responseData, HttpStatus.CREATED);
    }

    @PutMapping({ "/update", "/update/" })
    public ResponseEntity<ResponseData<?>> update(@RequestBody ClassUpdateRequestDto classUpdateRequestDto)
            throws HttpException {
        Teacher teacher = teacherService.findById(classUpdateRequestDto.getTeacherId());
        classUpdateRequestDto.setTeacher(teacher);
        List<Student> students = studentService.findStudents(classUpdateRequestDto.getStudentIds());
        classUpdateRequestDto.setStudents(students);
        classService.update(classUpdateRequestDto, classUpdateRequestDto.getId());
        
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.ACCEPTED, "Class is update", null);
        return new ResponseEntity<ResponseData<?>>(responseData, HttpStatus.ACCEPTED);
    }

    @DeleteMapping({ "/remove", "/remove/" })
    public ResponseEntity<ResponseData<?>> remove(@RequestBody ClassRemoveRequestDto classRemoveRequestDto)
            throws HttpException {
        classService.delete(classRemoveRequestDto, classRemoveRequestDto.getId());
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.ACCEPTED, "Class is deleted", null);
        return new ResponseEntity<ResponseData<?>>(responseData, HttpStatus.ACCEPTED);
    }
}

package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Class;
import com.example.demo.entities.User;
import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.dtos.requests.TeacherCreateRequestDto;
import com.example.demo.dtos.requests.TeacherRemoveRequestDto;
import com.example.demo.dtos.requests.TeacherUpdateRequestDto;
import com.example.demo.dtos.responses.TeacherResponseDto;
import com.example.demo.interfaces.services.IClassService;
import com.example.demo.interfaces.services.ITeacherService;
import com.example.demo.repositories.UserRepository;

import org.apache.commons.codec.digest.DigestUtils;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends AbController {
    @Autowired
    ITeacherService teacherService;

    @Autowired
    IClassService classService;

    @GetMapping({ "/", "" })
    public ResponseEntity<ResponseData<List<TeacherResponseDto>>> findAll() throws HttpException {
        List<TeacherResponseDto> data = teacherService.findAll(TeacherResponseDto.class);
        User user = (User)this.request.getAttribute("user");
        ResponseData<List<TeacherResponseDto>> responseData = new ResponseData<>(HttpStatus.OK, user.toString(),
                data);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity<ResponseData<TeacherResponseDto>> findById(@PathVariable UUID id) throws HttpException {
        TeacherResponseDto data = teacherService.findById(id, TeacherResponseDto.class);
        ResponseData<TeacherResponseDto> responseData = new ResponseData<>(HttpStatus.OK, "Student id=" + id.toString(),
                data);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "/create", "/create/" })
    public ResponseEntity<ResponseData<?>> create(@RequestBody TeacherCreateRequestDto teacherCreateRequestDto)
            throws HttpException {
        teacherService.create(teacherCreateRequestDto);
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "Teacher is created", null);
        return new ResponseEntity<ResponseData<?>>(responseData, HttpStatus.CREATED);
    }

    @PutMapping({ "/update", "/update/" })
    public ResponseEntity<ResponseData<?>> update(@RequestBody TeacherUpdateRequestDto teacherUpdateRequestDto)
            throws HttpException {
        teacherService.update(teacherUpdateRequestDto, teacherUpdateRequestDto.getId());
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.ACCEPTED, "Teacher is update", null);
        return new ResponseEntity<ResponseData<?>>(responseData, HttpStatus.ACCEPTED);
    }

    @DeleteMapping({ "/remove", "/remove/" })
    public ResponseEntity<ResponseData<?>> remove(@RequestBody TeacherRemoveRequestDto teacherRemoveRequestDto)
            throws HttpException {
        if(!this.teacherService.findById(teacherRemoveRequestDto.getId()).getClasses().isEmpty()){
            throw new HttpException(HttpStatus.BAD_REQUEST,"Please remove on class or set teacher for class of teacher");
        }
        teacherService.delete(teacherRemoveRequestDto, teacherRemoveRequestDto.getId());
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.ACCEPTED, "Teacher is deleted", null);
        return new ResponseEntity<ResponseData<?>>(responseData, HttpStatus.ACCEPTED);
    }
}

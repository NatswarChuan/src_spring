package com.example.demo.controllers;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.configs.JwtUtil;
import com.example.demo.dtos.requests.UserLoginRequestDto;
import com.example.demo.dtos.requests.UserRefreshRequestDto;
import com.example.demo.dtos.responses.UserLoginResponseDto;
import com.example.demo.dtos.responses.UserRefreshResponseDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserControler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping({"/login","/login/"})
    public ResponseEntity<ResponseData<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto userLoginRequestDto) throws HttpException {
        String password = DigestUtils.md5Hex(userLoginRequestDto.getPassword());
        String userName = userLoginRequestDto.getName();
        Optional<User> opnUser =  userRepository.findByNameAndPassword(userName, password);

        if(opnUser.isEmpty()){
            throw new HttpException(HttpStatus.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        }

        User user = opnUser.get();

        String accessToken = jwtUtil.generateToken(user.getId());
        user.setRefreshToken(UUID.randomUUID());
        userRepository.save(user);

        UserLoginResponseDto res = new UserLoginResponseDto(accessToken, user.getRefreshToken());

        ResponseData<UserLoginResponseDto> data = new ResponseData<>(HttpStatus.ACCEPTED, "Đăng nhập thành công",res);
        return new ResponseEntity<ResponseData<UserLoginResponseDto>>(data,HttpStatus.ACCEPTED);
    }

     @PostMapping({"/refresh","/refresh/"})
    public ResponseEntity<ResponseData<UserRefreshResponseDto>> refresh(@RequestBody UserRefreshRequestDto userRefreshRequestDto) throws HttpException{
        UUID refreshToken = userRefreshRequestDto.getRefreshToken();
        Optional<User> opnUser = userRepository.findByRefreshToken(refreshToken);
        if(opnUser.isEmpty()){
            throw new HttpException(HttpStatus.BAD_REQUEST, "refresh token không hợp lệ");
        }
         User user = opnUser.get();

        String accessToken = jwtUtil.generateToken(user.getId());
        user.setRefreshToken(UUID.randomUUID());
        userRepository.save(user);

        UserRefreshResponseDto res = new UserRefreshResponseDto(accessToken);

        ResponseData<UserRefreshResponseDto> data = new ResponseData<>(HttpStatus.ACCEPTED, "Tạo mới access token thành công",res);
        return new ResponseEntity<ResponseData<UserRefreshResponseDto>>(data,HttpStatus.ACCEPTED);
    }   
}

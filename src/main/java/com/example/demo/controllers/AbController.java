package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;


import jakarta.servlet.http.HttpServletRequest;

/**
 * Đây là một lớp trừu tượng đại diện cho một Controller trừu tượng.
 * Các Controller cụ thể sẽ kế thừa từ lớp này.
 */
public abstract class AbController {


    /**
     * Đối tượng HttpServletRequest được sử dụng để truy cập thông tin về HTTP
     * request.
     */
    @Autowired
    protected HttpServletRequest request;
}

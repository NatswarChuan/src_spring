package com.example.demo.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Đây là một lớp đại diện cho phản hồi khi xảy ra lỗi trong quá trình kiểm tra hợp lệ.
 * Lớp này chứa thông tin về trường (field) gây lỗi và thông báo (message) liên quan đến lỗi đó.
 */
@Data
@AllArgsConstructor
public class ValidationFailResponse {
    /**
     * Tên của trường gây lỗi.
     */
    @JsonProperty("field")
    String field;

    /**
     * Thông báo liên quan đến lỗi.
     */
    @JsonProperty("message")
    String message;
}

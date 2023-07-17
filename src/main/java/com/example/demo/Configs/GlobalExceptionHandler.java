package com.example.demo.configs;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.commond.HttpException;
import com.example.demo.commond.ResponseData;
import com.example.demo.dtos.responses.ValidationFailResponse;
import com.example.demo.services.LogService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * GlobalExceptionHandler là một RestControllerAdvice được sử dụng để xử lý các
 * ngoại lệ toàn cầu trong ứng dụng.
 * 
 * Nó xử lý các ngoại lệ liên quan đến xác nhận đối số phương thức và ngoại lệ
 * HttpException.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LogService logService;

    /**
     * 
     * Xử lý ngoại lệ MethodArgumentNotValidException, được kích hoạt khi các đối số
     * phương thức không hợp lệ.
     * 
     * @param ex MethodArgumentNotValidException: Ngoại lệ được ném khi các đối số
     *           phương thức không hợp lệ.
     * 
     * @return ResponseEntity<ResponseData<List<ValidationFailResponseDto>>>: Phản
     *         hồi HTTP chứa thông tin về các lỗi xác nhận đối số.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseData<List<ValidationFailResponse>>> handleValidationException(
            MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ValidationFailResponse> data = new ArrayList<>();

        for (FieldError error : result.getFieldErrors()) {
            ValidationFailResponse fieldFail = new ValidationFailResponse(error.getField(),
                    error.getDefaultMessage());
            data.add(fieldFail);
        }

        ResponseData<List<ValidationFailResponse>> response = new ResponseData<>(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), data);
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * 
     * Xử lý ngoại lệ HttpException, được kích hoạt khi xảy ra lỗi trong quá trình
     * xử lý yêu cầu HTTP.
     * 
     * @param ex HttpException: Ngoại lệ được ném khi xảy ra lỗi trong quá trình xử
     *           lý yêu cầu HTTP.
     * 
     * @return ResponseEntity<ResponseData<?>>: Phản hồi HTTP chứa thông tin về
     *         ngoại lệ và trạng thái.
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ResponseData<?>> handleException(HttpException ex) {
        String message = ex.getMessage();
        HttpStatus status = ex.getStatus();
        ResponseData<Object> response = new ResponseData<>(status,message,null);

        logService.exception(request.getRequestURI(), "exception: " + ex.getMessage());

        return ResponseEntity.status(status).body(response);
    }
}
package com.example.demo.middlewares;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.commond.ResponseData;
import com.example.demo.configs.JwtUtil;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Middleware để xác thực người dùng.
 *
 * <p>
 * Middleware này được sử dụng để xác thực và kiểm tra token của người dùng
 * trong request.
 * Nếu token hợp lệ và có quyền truy cập, middleware sẽ tiếp tục xử lý request.
 * Ngược lại, nếu không có token hoặc token không hợp lệ, middleware sẽ trả về
 * một phản hồi
 * không được ủy quyền (Unauthorized) cho client.
 * </p>
 */
@Configuration
public class AuthMiddleware implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogService logService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Phương thức được gọi trước khi xử lý request.
     *
     * @param request  Đối tượng HttpServletRequest đại diện cho request hiện tại.
     * @param response Đối tượng HttpServletResponse đại diện cho response sẽ được
     *                 trả về cho client.
     * @param handler  Đối tượng Object đại diện cho handler của request.
     * @return True nếu xác thực thành công và tiếp tục xử lý request, False nếu
     *         không xác thực thành công.
     * @throws Exception Nếu xảy ra lỗi trong quá trình xử lý.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new ResponseData<Object>(HttpStatus.BAD_REQUEST, "bad request", null));
            response.getWriter().write(json);
            logService.error(request.getRequestURI(), "JWT Authentication Error (token: " + token + " )");
            return false;
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtil.validateToken(token)) {
                UUID userId = jwtUtil.getUserIdFromToken(token);
                Optional<User> opUser = userRepository.findById(userId);
                if (opUser.isPresent()) {
                    request.setAttribute("user", opUser.get());
                    return true;
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new ResponseData<Object>(HttpStatus.UNAUTHORIZED, "unauthorized", null));
        response.getWriter().write(json);
        logService.error(request.getRequestURI(), "JWT Authentication Error (token: " + token + " )");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

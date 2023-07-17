package com.example.demo.Configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * Đây là một component sử dụng để thao tác với JWT (JSON Web Token).
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    /**
     * 
     * Sinh ra một JWT token cho một người dùng dựa trên userId.
     * 
     * @param userId ID của người dùng
     * 
     * @return Chuỗi JWT token đã sinh ra
     */
    public String generateToken(UUID userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", userId.toString());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * 
     * Lấy ra ID của người dùng từ JWT token.
     * 
     * @param token JWT token
     * 
     * @return UUID của người dùng
     */
    public UUID getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String userIdString = claims.get("user_id", String.class);
        return UUID.fromString(userIdString);
    }

    /**
     * 
     * Kiểm tra tính hợp lệ của JWT token.
     * 
     * @param token JWT token cần kiểm tra
     * @return true nếu token hợp lệ, ngược lại trả về false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
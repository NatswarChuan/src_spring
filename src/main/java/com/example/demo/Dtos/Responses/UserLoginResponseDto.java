package com.example.demo.dtos.responses;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginResponseDto {
    @JsonProperty("access-token")
    String accessToken;

    @JsonProperty("refresh-token")
    UUID refreshToken;
}

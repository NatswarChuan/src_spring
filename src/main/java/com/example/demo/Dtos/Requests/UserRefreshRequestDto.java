package com.example.demo.dtos.requests;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRefreshRequestDto {
    @JsonProperty("refresh-token")
    UUID refreshToken;
}

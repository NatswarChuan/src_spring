package com.example.demo.dtos.requests;

import com.example.demo.entities.User;
import com.example.demo.interfaces.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginRequestDto {
    @JsonProperty("user-name")
    @NotEmpty(message = "user name is required")
    String name;
    
    @JsonProperty("password")
    @NotEmpty(message = "password is required")
    String password;
}

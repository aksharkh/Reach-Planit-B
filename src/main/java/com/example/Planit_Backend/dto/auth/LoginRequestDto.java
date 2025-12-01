package com.example.Planit_Backend.dto.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {


    @NotBlank(message = "email should not be blank")
    private String email;
    @NotBlank(message = "password should not be empty")
    private String password;
}

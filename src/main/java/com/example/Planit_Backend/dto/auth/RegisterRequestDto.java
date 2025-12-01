package com.example.Planit_Backend.dto.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "username shouldn't be empty")
    private String userName;
    private String role;

    private String email;
    private String password;


}

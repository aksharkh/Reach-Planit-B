package com.example.Planit_Backend.dto;


import lombok.Data;

@Data
public class RegisterRequestDto {


    private String userName;
    private String role;
    private String email;
    private String password;


}

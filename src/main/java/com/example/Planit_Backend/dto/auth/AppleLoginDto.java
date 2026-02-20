package com.example.Planit_Backend.dto.auth;


import lombok.Data;

@Data
public class AppleLoginDto {

    private String identityToken;
    private String user;  // Apple returns user info as JSON string
}

package com.example.Planit_Backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {

    private String jwtToken;
//    private String refreshToken;
    private String tokenType = "Bearer";
}

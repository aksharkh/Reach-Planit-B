package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.auth.LoginRequestDto;
import com.example.Planit_Backend.dto.auth.AuthResponseDto;
import com.example.Planit_Backend.dto.auth.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto requestDto);
    AuthResponseDto register(RegisterRequestDto requestDto);
//    AuthResponseDto googleLogin(String idToken);
    String loginWithGoogle(String idToken);
}

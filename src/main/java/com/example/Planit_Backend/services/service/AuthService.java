package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.LoginRequestDto;
import com.example.Planit_Backend.dto.AuthResponseDto;
import com.example.Planit_Backend.dto.RegisterRequestDto;
import com.example.Planit_Backend.dto.UserDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto requestDto);
    AuthResponseDto register(RegisterRequestDto requestDto);
//    AuthResponseDto googleLogin(String idToken);
    String loginWithGoogle(String idToken);
}

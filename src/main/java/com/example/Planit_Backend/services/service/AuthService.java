package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.AuthRequestDto;
import com.example.Planit_Backend.dto.AuthResponseDto;
import com.example.Planit_Backend.dto.UserDto;

public interface AuthService {

    AuthResponseDto login(AuthRequestDto requestDto);
    AuthResponseDto register(UserDto userDto, String password);
    AuthResponseDto googleLogin(String idToken);
}

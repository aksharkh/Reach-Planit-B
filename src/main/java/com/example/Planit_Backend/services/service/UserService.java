package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.UserDto;
import com.example.Planit_Backend.dto.requestDtos.UserUpdateRequestDto;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserDto currentUserDetails(Authentication authentication);

    UserDto updateUser(Long userId, UserUpdateRequestDto request);
    void deleteUser(Long userId);
}

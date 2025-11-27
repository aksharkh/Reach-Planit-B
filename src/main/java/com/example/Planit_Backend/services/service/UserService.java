package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.UserDto;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserDto currentUserDetails(Authentication authentication);
}

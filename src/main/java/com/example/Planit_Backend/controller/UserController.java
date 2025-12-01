package com.example.Planit_Backend.controller;



import com.example.Planit_Backend.dto.UserDto;
import com.example.Planit_Backend.dto.requestDtos.UserUpdateRequestDto;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(Authentication authentication) {
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        UserDto userDto = userService.currentUserDetails(authentication);
        return ResponseEntity.ok(userDto);


    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updatedUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }




}

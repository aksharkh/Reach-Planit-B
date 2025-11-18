package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.GoogleLoginDto;
import com.example.Planit_Backend.dto.LoginRequestDto;
import com.example.Planit_Backend.dto.AuthResponseDto;
import com.example.Planit_Backend.dto.RegisterRequestDto;
import com.example.Planit_Backend.services.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto req){
        return  ResponseEntity.ok(authService.register(req));
    }

//    @PostMapping("/google")
//    public ResponseEntity<AuthResponseDto>  googleLogin(@RequestBody GoogleLoginDto req){
//        return ResponseEntity.ok(authService.googleLogin(req.getIdToken()));
//    }
}

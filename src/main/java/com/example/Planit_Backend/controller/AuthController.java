package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.auth.GoogleLoginDto;
import com.example.Planit_Backend.dto.auth.LoginRequestDto;
import com.example.Planit_Backend.dto.auth.AuthResponseDto;
import com.example.Planit_Backend.dto.auth.RegisterRequestDto;
import com.example.Planit_Backend.services.service.AuthService;
import com.example.Planit_Backend.utils.GoogleTokenVerifier;
import com.example.Planit_Backend.utils.JwtUtils;
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
    private final GoogleTokenVerifier googleTokenVerifier;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto req){
        return  ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginDto request) {

       String jwt = authService.loginWithGoogle(request.getIdToken());
       return ResponseEntity.ok(jwt);
    }
}

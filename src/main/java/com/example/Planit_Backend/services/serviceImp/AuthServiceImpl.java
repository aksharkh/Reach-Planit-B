package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.auth.LoginRequestDto;
import com.example.Planit_Backend.dto.auth.AuthResponseDto;
import com.example.Planit_Backend.dto.auth.RegisterRequestDto;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.exceptions.EmailAlreadyExistsException;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.AuthService;
import com.example.Planit_Backend.utils.GoogleTokenVerifier;
import com.example.Planit_Backend.utils.AppleTokenVerifier;
import com.example.Planit_Backend.utils.JwtUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;
    private final GoogleTokenVerifier googleTokenVerifier;
    private final AppleTokenVerifier appleTokenVerifier;


    @Override
    public AuthResponseDto login(LoginRequestDto request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new RuntimeException("Invalid credentials");
        }

        String jwtToken = jwtUtils.generateJwtToken(user.getEmail());
//        String refresh = jwtUtil
        return new AuthResponseDto(jwtToken, "Bearer");


    }

    @Override
    public AuthResponseDto register(RegisterRequestDto req){
        if(userRepository.existsByEmail(req.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = modelMapper.map(req, User.class);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setProvider("local");

        userRepository.save(user);

        String jwtToken = jwtUtils.generateJwtToken(user.getEmail());

        return new AuthResponseDto(jwtToken, "Bearer");

    }

    @Override
    public String loginWithGoogle(String idToken) {

        GoogleIdToken.Payload payload = googleTokenVerifier.verify(idToken);

        if(payload == null) {
            throw new RuntimeException("Invalid Google ID token");
        }

        String email = payload.getEmail();
        String name = (String) payload.get("name");

        User user = userRepository.findByEmail(email).orElse(null);

        if(user == null) {
            user = new User();
            user.setEmail(email);
            user.setUserName(name);
            user.setProvider("GOOGLE");
            userRepository.save(user);
        }

        return jwtUtils.generateJwtToken(email);
    }

    @Override
    public String loginWithApple(String identityToken) {

        AppleTokenVerifier.AppleTokenPayload payload = appleTokenVerifier.verify(identityToken);

        if(payload == null) {
            throw new RuntimeException("Invalid Apple ID token");
        }

        String email = payload.email;

        User user = userRepository.findByEmail(email).orElse(null);

        if(user == null) {
            user = new User();
            user.setEmail(email);
            user.setUserName(email.split("@")[0]); // Use part of email as username
            user.setProvider("APPLE");
            userRepository.save(user);
        }

        return jwtUtils.generateJwtToken(email);
    }
}

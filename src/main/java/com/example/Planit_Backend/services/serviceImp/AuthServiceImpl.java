package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.LoginRequestDto;
import com.example.Planit_Backend.dto.AuthResponseDto;
import com.example.Planit_Backend.dto.RegisterRequestDto;
import com.example.Planit_Backend.dto.UserDto;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.exceptions.EmailAlreadyExistsException;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.AuthService;
import com.example.Planit_Backend.utils.JwtUtils;
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

//    @Override
//    public AuthResponseDto googleLogin(String idToken){
//
//        String email = verifyGoo
//
//    }


}

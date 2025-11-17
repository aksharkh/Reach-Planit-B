package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.AuthRequestDto;
import com.example.Planit_Backend.dto.AuthResponseDto;
import com.example.Planit_Backend.dto.UserDto;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Override
    public AuthResponseDto login(AuthRequestDto request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if(user.getPassword() == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new RuntimeException("Invalid credentials");
        }


    }

    @Override
    public AuthResponseDto register(UserDto userDto, String password){
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(password));
        user.setProvider("local");

        userRepository.save(user);
        retu
    }

    @Override
    public AuthResponseDto googleLogin(String idToken){

    }


}

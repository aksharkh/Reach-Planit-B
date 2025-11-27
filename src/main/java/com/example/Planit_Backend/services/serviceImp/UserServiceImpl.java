package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.UserDto;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;


    @Override
    public UserDto currentUserDetails(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        return modelMapper.map(user, UserDto.class);
    }
}

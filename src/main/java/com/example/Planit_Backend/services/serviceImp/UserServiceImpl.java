package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.UserDto;
import com.example.Planit_Backend.dto.requestDtos.UserUpdateRequestDto;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto currentUserDetails(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateRequestDto request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(request.getUserName() != null) user.setUserName(request.getUserName());
        if(request.getEmail() != null) user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }


    @Override
    public void  deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

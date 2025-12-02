package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.familyDtos.FamilyMemberRequestDto;
import com.example.Planit_Backend.dto.familyDtos.FamilyMemberResponse;
import com.example.Planit_Backend.entity.FamilyMember;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.FamilyMembersRepository;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.FamilyMembersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyMembersServiceImpl implements FamilyMembersService {


    private final UserRepository userRepository;
    private final FamilyMembersRepository familyMembersRepository;
    private final ModelMapper modelMapper;

    @Override
    public FamilyMemberResponse addMember(FamilyMemberRequestDto request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));


        FamilyMember member = new FamilyMember();
        member.setName(request.getName());
        member.setRelation(request.getRelation());
        member.setDob(request.getDob());
        member.setGender(request.getGender());
        member.setInterests(request.getInterests());
        member.setHobbies(request.getHobbies());
        member.setUser(user);

        FamilyMember saved = familyMembersRepository.save(member);

        return modelMapper.map(saved, FamilyMemberResponse.class);
    }


//    @Override
//    public FamilyMemberResponse getMembers(Long userId){
//
//    }
}

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyMembersServiceImpl implements FamilyMembersService {


    private final UserRepository userRepository;
    private final FamilyMembersRepository familyMembersRepository;
    private final ModelMapper modelMapper;


    private FamilyMemberResponse mapToResponse(FamilyMember member) {
        FamilyMemberResponse res = modelMapper.map(member, FamilyMemberResponse.class);

        if(member.getDob()!= null) {
            res.setAge(Period.between(member.getDob(), LocalDate.now()).getYears());
        }

        return res;
    }

    @Override
    public FamilyMemberResponse addMember(Long userId, FamilyMemberRequestDto request){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));


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


    @Override
    public List<FamilyMemberResponse> getMembers(Long userId){
        List<FamilyMember> members = familyMembersRepository.findByUserId(userId);

        return  members.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public FamilyMemberResponse updateMember(Long userId, Long memberId, FamilyMemberRequestDto request){
        FamilyMember member = familyMembersRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

        if(!member.getUser().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete the member");
        }

        modelMapper.map(request, member);

        FamilyMember updated = familyMembersRepository.save(member);

        return mapToResponse(updated);
    }


    @Override
    public void deleteMember(Long userId, Long memberId) {
        FamilyMember member = familyMembersRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

        if(!member.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this member");
        }

        familyMembersRepository.delete(member);
    }
}

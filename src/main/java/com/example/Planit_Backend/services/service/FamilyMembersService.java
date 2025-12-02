package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.familyDtos.FamilyMemberRequestDto;
import com.example.Planit_Backend.dto.familyDtos.FamilyMemberResponse;

public interface FamilyMembersService {

    FamilyMemberResponse addMember(FamilyMemberRequestDto request);
//    FamilyMemberResponse getMembers(Long userId);

}

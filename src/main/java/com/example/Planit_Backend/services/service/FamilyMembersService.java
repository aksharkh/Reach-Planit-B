package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.familyDtos.FamilyMemberRequestDto;
import com.example.Planit_Backend.dto.familyDtos.FamilyMemberResponse;

import java.util.List;

public interface FamilyMembersService {

    FamilyMemberResponse addMember(Long userId, FamilyMemberRequestDto request);
    List<FamilyMemberResponse> getMembers(Long userId);
    FamilyMemberResponse updateMember(Long userId, Long memberId, FamilyMemberRequestDto request);
    void deleteMember(Long userId, Long memberId);


}

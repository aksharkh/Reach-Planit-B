package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.familyDtos.FamilyMemberRequestDto;
import com.example.Planit_Backend.dto.familyDtos.FamilyMemberResponse;
import com.example.Planit_Backend.services.service.FamilyMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/family-member")
@RequiredArgsConstructor
public class FamilyMembersController {

    private final FamilyMembersService familyMembersService;

    @PostMapping
    public ResponseEntity<FamilyMemberResponse> addMember(@RequestBody FamilyMemberRequestDto request){
        return new ResponseEntity<>(familyMembersService.addMember(request), HttpStatus.CREATED);
    }
}

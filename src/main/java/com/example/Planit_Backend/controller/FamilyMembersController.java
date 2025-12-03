package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.familyDtos.FamilyMemberRequestDto;
import com.example.Planit_Backend.dto.familyDtos.FamilyMemberResponse;
import com.example.Planit_Backend.services.service.FamilyMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family-member")
@RequiredArgsConstructor
public class FamilyMembersController {

    private final FamilyMembersService familyMembersService;

    @PostMapping("/{userId}")
    public ResponseEntity<FamilyMemberResponse> addMember(@PathVariable Long userId ,@RequestBody FamilyMemberRequestDto request){
        return new ResponseEntity<>(familyMembersService.addMember(userId, request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FamilyMemberResponse>> getMembers(@PathVariable Long userId) {
        return ResponseEntity.ok(familyMembersService.getMembers(userId));
    }

    @PutMapping("/{userId}/{memberId}")
    public ResponseEntity<FamilyMemberResponse> updateMember(@PathVariable Long userId, @PathVariable Long memberId, @RequestBody FamilyMemberRequestDto request) {
        return ResponseEntity.ok(familyMembersService.updateMember(userId, memberId, request));
    }

    @DeleteMapping("/{userId}/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Long userId, @PathVariable Long memberId) {
        familyMembersService.deleteMember(userId, memberId);
        return  ResponseEntity.ok("member deleted");
    }
}

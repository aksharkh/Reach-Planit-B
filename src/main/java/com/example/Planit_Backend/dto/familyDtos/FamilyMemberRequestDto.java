package com.example.Planit_Backend.dto.familyDtos;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FamilyMemberRequestDto {

    private String name;
    private String relation;
    private LocalDateTime dob;
    private String gender;
    private List<String> interests;
    private  List<String> hobbies;
    private Long userId;


}

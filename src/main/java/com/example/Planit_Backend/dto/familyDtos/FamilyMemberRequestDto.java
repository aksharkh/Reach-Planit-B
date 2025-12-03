package com.example.Planit_Backend.dto.familyDtos;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FamilyMemberRequestDto {

    private String name;
    private String relation;
    private LocalDate dob;
    private String gender;
    private List<String> interests;
    private  List<String> hobbies;



}

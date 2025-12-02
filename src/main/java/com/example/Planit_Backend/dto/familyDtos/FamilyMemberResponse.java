package com.example.Planit_Backend.dto.familyDtos;


import lombok.Data;

@Data
public class FamilyMemberResponse {

    private Long id;
    private String name;
    private String relation;
    private Integer age;
}

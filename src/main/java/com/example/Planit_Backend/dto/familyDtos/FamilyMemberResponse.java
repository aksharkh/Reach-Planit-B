package com.example.Planit_Backend.dto.familyDtos;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FamilyMemberResponse {

    private Long id;
    private String name;
    private String relation;
    private String gender;
    private Integer age;
    private LocalDate dob;
    private List<String> interests;
    private List<String> hobbies;
}

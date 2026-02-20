package com.example.Planit_Backend.dto.requestDtos;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventRequestDto {


    private String description;
    private LocalDate birthDate;
    private LocalDate eventDate;
//    private Long userId;
    private Long categoryId;
    private String milestoneType;
    private Long memberId;
    private String customMilestone;
//    private LocalDateTime reminderAt;
}

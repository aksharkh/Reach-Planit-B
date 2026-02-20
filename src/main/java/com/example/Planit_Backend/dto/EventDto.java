package com.example.Planit_Backend.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventDto {

    private Long id;
    private String description;
    private LocalDate eventDate;
    private String categoryName;
    private Long userId;
    private Long categoryId;
    private String milestoneType;
    private Long memberId;
    private String memberName;
    private String customMilestone;
    private Long daysLeft;
    private Long hoursLeft;
    private Long minutesLeft;

}

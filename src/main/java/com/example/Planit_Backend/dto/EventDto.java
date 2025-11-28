package com.example.Planit_Backend.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String categoryName;

    private Long daysLeft;
    private Long hoursLeft;
    private Long minutesLeft;

}

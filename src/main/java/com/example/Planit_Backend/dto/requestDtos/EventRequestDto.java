package com.example.Planit_Backend.dto.requestDtos;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequestDto {

    private String title;
    private String description;
    private LocalDateTime eventDate;
    private Long userId;
    private Long categoryId;

}

package com.example.Planit_Backend.dto.requestDtos;


import lombok.Data;

@Data
public class ShoppingStatRequestDto {


    private String label;
    private Double amount;
    private String period;
    private Long userId;
}

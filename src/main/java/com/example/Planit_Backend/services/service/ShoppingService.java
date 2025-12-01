package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.ShoppingStatDto;
import com.example.Planit_Backend.dto.requestDtos.ShoppingStatRequestDto;

public interface ShoppingService {

    ShoppingStatDto addStat(ShoppingStatRequestDto request);
    ShoppingStatDto updateStat(Long id, ShoppingStatRequestDto request);
    void deleteStat(Long id);
}

package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.DashboardResponseDto;

public interface DashboardService {

    DashboardResponseDto getDashboardData(Long userId);
}

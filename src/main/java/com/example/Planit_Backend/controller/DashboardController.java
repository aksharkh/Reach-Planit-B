package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.DashboardResponseDto;
import com.example.Planit_Backend.services.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DashboardController {


    private final DashboardService dashboardService;

    @GetMapping("/{userId}")
    public ResponseEntity<DashboardResponseDto> getDashBoard(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getDashboardData(userId));
    }
}

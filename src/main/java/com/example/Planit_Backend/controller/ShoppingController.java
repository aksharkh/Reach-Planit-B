package com.example.Planit_Backend.controller;

import com.example.Planit_Backend.dto.ShoppingStatDto;
import com.example.Planit_Backend.dto.requestDtos.ShoppingStatRequestDto;
import com.example.Planit_Backend.services.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingService shoppingService;

    @PostMapping
    public ResponseEntity<ShoppingStatDto> addStat(@RequestBody ShoppingStatRequestDto request) {
        return new ResponseEntity<>(shoppingService.addStat(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingStatDto> updateStat(@PathVariable Long id, @RequestBody ShoppingStatRequestDto request) {
        return ResponseEntity.ok(shoppingService.updateStat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStat(@PathVariable Long id) {
        shoppingService.deleteStat(id);
        return ResponseEntity.ok("Shopping data deleted");
    }
}

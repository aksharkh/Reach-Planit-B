package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.EventDto;
import com.example.Planit_Backend.dto.requestDtos.EventRequestDto;
import com.example.Planit_Backend.services.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventRequestDto request) {
        return new ResponseEntity<>(eventService.createEvent(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Deleted");
    }
}

package com.example.Planit_Backend.controller;


import com.example.Planit_Backend.dto.EventDto;
import com.example.Planit_Backend.dto.requestDtos.EventRequestDto;
import com.example.Planit_Backend.services.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/{id}")
    public ResponseEntity<EventDto> createEvent(@PathVariable Long id,@RequestBody EventRequestDto request) {
        return new ResponseEntity<>(eventService.createEvent(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventRequestDto req) {
        return ResponseEntity.ok(eventService.updateEvent(id, req));
    }

    @GetMapping("/{userId}/{eventId}")
    public ResponseEntity<EventDto> getEventDetails(@PathVariable Long userId, @PathVariable Long eventId){
        return ResponseEntity.ok(eventService.getEventDetails(userId, eventId));
    }
}

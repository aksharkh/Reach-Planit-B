package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.EventDto;
import com.example.Planit_Backend.dto.requestDtos.EventRequestDto;

public interface EventService {

    EventDto createEvent(EventRequestDto request);
    EventDto updateEvent(Long evntId, EventRequestDto request);
    void deleteEvent(Long eventId);
}

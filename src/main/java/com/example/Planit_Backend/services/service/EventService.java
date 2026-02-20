package com.example.Planit_Backend.services.service;

import com.example.Planit_Backend.dto.EventDto;
import com.example.Planit_Backend.dto.requestDtos.EventRequestDto;

public interface EventService {

    EventDto createEvent(Long id,EventRequestDto request);
    EventDto getEventDetails(Long userId, Long eventId);
    EventDto updateEvent(Long eventId, EventRequestDto request);
    void deleteEvent(Long eventId);
}


//userId: number;
//categoryId: number;
//memberId: number;
//eventDate: string;
//description: string;
//milestoneType: string;
//customMilestone?: string | null;


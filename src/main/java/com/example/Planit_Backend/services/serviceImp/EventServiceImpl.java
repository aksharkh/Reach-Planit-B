package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.EventDto;
import com.example.Planit_Backend.dto.requestDtos.EventRequestDto;
import com.example.Planit_Backend.entity.Category;
import com.example.Planit_Backend.entity.Event;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.CategoryRepository;
import com.example.Planit_Backend.repository.EventRepository;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    private EventDto mapToDto(Event event) {
        EventDto dto = modelMapper.map(event, EventDto.class);
        if(event.getCategory()!= null) {
            dto.setCategoryName(event.getCategory().getName());
        }

        if(event.getEventDate()!= null) {
            Duration diff = Duration.between(LocalDateTime.now(), event.getEventDate());

            dto.setDaysLeft(diff.toDays());
            dto.setHoursLeft(diff.toHours() % 24);
            dto.setMinutesLeft(diff.toMinutes() % 60);
        }

        return dto;
    }


    @Override
    public EventDto createEvent(EventRequestDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

//        Event event = modelMapper.map(request, Event.class);
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setUser(user);
        event.setCategory(category);

        Event savedEvent = eventRepository.save(event);
        return mapToDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(Long eventId, EventRequestDto request){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        if(request.getTitle() != null) event.setTitle(request.getTitle());
        if(request.getDescription() != null) event.setDescription(event.getDescription());
        if(request.getEventDate() != null) event.setEventDate(event.getEventDate());

        if(request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
            event.setCategory(category);
        }

        Event updatedEvent = eventRepository.save(event);
        return mapToDto(updatedEvent);
    }


    @Override
    public void deleteEvent(Long eventId) {
        if(!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }

        eventRepository.deleteById(eventId);
    }
}

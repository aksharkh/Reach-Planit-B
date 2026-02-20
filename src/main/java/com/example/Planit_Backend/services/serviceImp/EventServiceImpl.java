package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.EventDto;
import com.example.Planit_Backend.dto.requestDtos.EventRequestDto;
import com.example.Planit_Backend.entity.Category;
import com.example.Planit_Backend.entity.Event;
import com.example.Planit_Backend.entity.FamilyMember;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.CategoryRepository;
import com.example.Planit_Backend.repository.EventRepository;
import com.example.Planit_Backend.repository.FamilyMembersRepository;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FamilyMembersRepository familyMembersRepository;
    private final ModelMapper modelMapper;


    private EventDto mapToDto(Event event) {
        EventDto dto = modelMapper.map(event, EventDto.class);
        if(event.getCategory()!= null) {
            dto.setCategoryName(event.getCategory().getName());
        }

        if(event.getFamilyMember()!= null) {
            dto.setMemberName(event.getFamilyMember().getName());
        }

        if (event.getEventDate() != null) {
            LocalDateTime reminderTime = event.getEventDate().atStartOfDay();
            Duration diff = Duration.between(LocalDateTime.now(), reminderTime);

            if (diff.isNegative()) {
                dto.setDaysLeft(0L);
                dto.setHoursLeft(0L);
                dto.setMinutesLeft(0L);
            } else {
                dto.setDaysLeft(diff.toDays());
                dto.setHoursLeft(diff.toHours() % 24);
                dto.setMinutesLeft(diff.toMinutes() % 60);
            }
        }


        return dto;
    }


    @Override
    public EventDto createEvent(Long id, EventRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        FamilyMember member = familyMembersRepository.findById(request.getMemberId()).orElseThrow(() -> new RuntimeException("Member not found"));
//        Event event = modelMapper.map(request, Event.class);
        Event event = new Event();
        event.setDescription(request.getDescription());
        event.setBirthDate(request.getBirthDate());
        event.setEventDate(request.getEventDate());
        event.setMilestoneType(request.getMilestoneType());
        event.setUser(user);
        event.setCategory(category);
        event.setFamilyMember(member);
        if( request.getCustomMilestone() != null && !request.getCustomMilestone().isEmpty()) {
            event.setCustomMilestone(request.getCustomMilestone());
        }

        Event savedEvent = eventRepository.save(event);
        return mapToDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(Long eventId, EventRequestDto request){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));


        if(request.getDescription() != null) event.setDescription(request.getDescription());
        if(request.getEventDate() != null) event.setEventDate(request.getEventDate());
        if(request.getMilestoneType() != null) event.setMilestoneType(request.getMilestoneType());
        if(request.getCustomMilestone() != null) event.setCustomMilestone(request.getCustomMilestone());

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


    @Override
    public EventDto getEventDetails(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        if(!event.getUser().getId().equals(userId)) {
            throw  new RuntimeException("You are not allowed to see this event");
        }

        return mapToDto(event);


    }
}

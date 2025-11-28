package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.*;
import com.example.Planit_Backend.entity.Category;
import com.example.Planit_Backend.entity.Event;
import com.example.Planit_Backend.entity.ShoppingStat;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.CategoryRepository;
import com.example.Planit_Backend.repository.EventRepository;
import com.example.Planit_Backend.repository.ShoppingStatRepository;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

        private final UserRepository userRepository;
        private final EventRepository eventRepository;
        private final CategoryRepository categoryRepository;
        private final ShoppingStatRepository shoppingStatRepository;
        private final ModelMapper modelMapper;


        @Override
        public DashboardResponseDto getDashboardData(Long userId) {

            DashboardResponseDto response = new DashboardResponseDto();

            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            response.setUserProfile(modelMapper.map(user, UserDto.class));

            List<Event> upcomingEvents = eventRepository.findByUserIdAndEventDateAfterOrderByEventDateAsc(userId, LocalDateTime.now());

            if(!upcomingEvents.isEmpty()) {
                Event nearestEvent = upcomingEvents.get(0);
                EventDto eventDto = modelMapper.map(nearestEvent, EventDto.class);


                Duration diff = Duration.between(LocalDateTime.now(), nearestEvent.getEventDate());
                eventDto.setDaysLeft(diff.toDays());
                eventDto.setHoursLeft(diff.toHours() % 24);
                eventDto.setMinutesLeft(diff.toMinutes() % 60);

                response.setUpcomingEvent(eventDto);
            }

            LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
            LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);
            Long todayCount = eventRepository.countEventsForDay(userId, startOfDay, endOfDay);
            response.setEventsTodayCount(todayCount);

            List<ShoppingStat> stats = shoppingStatRepository.findByUserId(userId);
            List<ShoppingStatDto> statDtos = stats.stream()
                    .map(s -> modelMapper.map(s, ShoppingStatDto.class))
                    .collect(Collectors.toList());
            response.setShoppingData(statDtos);


            List<Category> allCategories = categoryRepository.findAll();

            response.setFeaturedCategories(allCategories.stream()
                    .filter(Category::isFeatured)
                    .map(c -> modelMapper.map(c, CategoryDto.class))
                    .collect(Collectors.toList()));

            response.setMoreCategories(allCategories.stream()
                    .filter(c -> !c.isFeatured())
                    .map(c-> modelMapper.map(c, CategoryDto.class))
                    .collect(Collectors.toList()));


            return response;

         }


}

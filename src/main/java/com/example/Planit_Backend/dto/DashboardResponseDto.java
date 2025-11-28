package com.example.Planit_Backend.dto;


import lombok.Data;

import java.util.List;

@Data
public class DashboardResponseDto {


    private UserDto userProfile;
    private EventDto upcomingEvent;
    private Long eventsTodayCount;
    private List<ShoppingStatDto> shoppingData;
    private List<CategoryDto> featuredCategories;
    private List<CategoryDto> moreCategories;


}

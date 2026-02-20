package com.example.Planit_Backend.services.service.helper;

import com.example.Planit_Backend.dto.helperDtos.BirthdayTypesReqDto;
import com.example.Planit_Backend.dto.helperDtos.BirthdayTypesResDto;
import com.example.Planit_Backend.entity.BirthdayType;

import java.util.List;

public interface HelperService {


    BirthdayTypesResDto createBirthdayType(BirthdayTypesReqDto req);
    BirthdayTypesResDto updateBirthdayType(Long id,BirthdayTypesReqDto req);
    List<BirthdayTypesResDto> getAllBirthdayType();
    void deleteBirthdayType(Long id);

}

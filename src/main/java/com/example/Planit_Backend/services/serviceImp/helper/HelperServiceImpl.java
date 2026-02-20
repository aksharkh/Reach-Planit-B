package com.example.Planit_Backend.services.serviceImp.helper;

import com.example.Planit_Backend.dto.helperDtos.BirthdayTypesReqDto;
import com.example.Planit_Backend.dto.helperDtos.BirthdayTypesResDto;
import com.example.Planit_Backend.entity.BirthdayType;
import com.example.Planit_Backend.repository.helperRepositories.BirthdayTypeRepository;
import com.example.Planit_Backend.services.service.helper.HelperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HelperServiceImpl implements HelperService {

    private  final BirthdayTypeRepository birthdayTypeRepository;
    private final ModelMapper modelMapper;


    @Override
    public BirthdayTypesResDto createBirthdayType(BirthdayTypesReqDto req) {

        BirthdayType newType = modelMapper.map(req, BirthdayType.class);

        BirthdayType saved = birthdayTypeRepository.save(newType);

        return modelMapper.map(saved, BirthdayTypesResDto.class);

    }



    @Override
    public BirthdayTypesResDto updateBirthdayType(Long id, BirthdayTypesReqDto req){

        BirthdayType birthdayType = birthdayTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("BirthdayType not found"));

        modelMapper.map(req, birthdayType);

        BirthdayType updated = birthdayTypeRepository.save(birthdayType);

        return modelMapper.map(updated, BirthdayTypesResDto.class);

    }

    @Override
    public void deleteBirthdayType(Long id){
        if (!birthdayTypeRepository.existsById(id)) {
            throw new RuntimeException("BirthdayType not found");

        }

        birthdayTypeRepository.deleteById(id);
    }

    @Override
    public List<BirthdayTypesResDto> getAllBirthdayType(){
        List<BirthdayType> birthdayTypes = birthdayTypeRepository.findAll();

        return birthdayTypes.stream().map(type -> modelMapper.map(type, BirthdayTypesResDto.class)).collect(Collectors.toList());
    }
}

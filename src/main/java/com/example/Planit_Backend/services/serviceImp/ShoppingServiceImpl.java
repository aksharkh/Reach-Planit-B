package com.example.Planit_Backend.services.serviceImp;

import com.example.Planit_Backend.dto.ShoppingStatDto;
import com.example.Planit_Backend.dto.requestDtos.ShoppingStatRequestDto;
import com.example.Planit_Backend.entity.ShoppingStat;
import com.example.Planit_Backend.entity.User;
import com.example.Planit_Backend.repository.ShoppingStatRepository;
import com.example.Planit_Backend.repository.UserRepository;
import com.example.Planit_Backend.services.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {


    private final ShoppingStatRepository shoppingStatRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public ShoppingStatDto addStat(ShoppingStatRequestDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        ShoppingStat stat = new ShoppingStat();
        stat.setLabel(request.getLabel());
        stat.setAmount(request.getAmount());
        stat.setPeriod(request.getPeriod());
        stat.setUser(user);

        ShoppingStat savedStat = shoppingStatRepository.save(stat);

        return  modelMapper.map(savedStat, ShoppingStatDto.class);
    }

    @Override
    public ShoppingStatDto updateStat(Long id, ShoppingStatRequestDto request){
        ShoppingStat stat = shoppingStatRepository.findById(id).orElseThrow(() -> new RuntimeException("Stat not found"));

        if(request.getLabel() != null) stat.setLabel(request.getLabel());
        if(request.getAmount() != null) stat.setAmount(request.getAmount());


        ShoppingStat updatedStat = shoppingStatRepository.save(stat);

        return modelMapper.map(updatedStat, ShoppingStatDto.class);
    }

    @Override
    public void deleteStat(Long id) {
        shoppingStatRepository.deleteById(id);
    }
}

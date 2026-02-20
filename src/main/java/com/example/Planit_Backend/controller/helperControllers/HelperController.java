package com.example.Planit_Backend.controller.helperControllers;


import com.example.Planit_Backend.dto.helperDtos.BirthdayTypesReqDto;
import com.example.Planit_Backend.dto.helperDtos.BirthdayTypesResDto;
import com.example.Planit_Backend.services.service.helper.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/helper")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class HelperController {

    private final HelperService helperService;


    @PostMapping("/birthdayType")
    public ResponseEntity<BirthdayTypesResDto> createBirthdayType(@RequestBody BirthdayTypesReqDto req, Authentication authentication) {

//        System.out.println("Authentication :" + authentication);
//        System.out.println("Authorities :" + authentication.getAuthorities());
        return  new ResponseEntity<>(helperService.createBirthdayType(req), HttpStatus.CREATED);
    }

    @GetMapping("/birthdayType")
    public ResponseEntity<List<BirthdayTypesResDto>> getAllBirthdayType(){
        return ResponseEntity.ok(helperService.getAllBirthdayType());
    }

    @PutMapping("/birthdayType/{id}")
    public ResponseEntity<BirthdayTypesResDto> updateBirthdayType(@PathVariable Long id, @RequestBody BirthdayTypesReqDto req){

        return ResponseEntity.ok(helperService.updateBirthdayType(id, req));

    }

    @DeleteMapping("/birthdayType/{id}")
    public ResponseEntity<String> deleteBirthdayType(@PathVariable Long id){

        helperService.deleteBirthdayType(id);
        return ResponseEntity.ok("birthdayType deleted");
    }


}
















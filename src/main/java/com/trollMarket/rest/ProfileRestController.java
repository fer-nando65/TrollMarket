package com.trollMarket.rest;

import com.trollMarket.dto.Profile.ProfileAddBalanceDTO;
import com.trollMarket.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/profile")
public class ProfileRestController {
    private final ProfileService service;

    @Autowired
    public ProfileRestController(ProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> postBalance(@Valid @RequestBody ProfileAddBalanceDTO dto,
                                              BindingResult validation){
        try {
            if(!validation.hasErrors()){
                this.service.addBalance(dto);
                return ResponseEntity.status(HttpStatus.OK).body("Balance saved");
            }else {
                var error = validation.getFieldError("balance").getDefaultMessage();
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
            }
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Runtime Error");
        }

    }
}

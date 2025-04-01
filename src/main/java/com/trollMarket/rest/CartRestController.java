package com.trollMarket.rest;

import com.trollMarket.dto.Cart.CartErrorDTO;
import com.trollMarket.dto.Cart.CartRawDTO;
import com.trollMarket.service.CartService;
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
@RequestMapping("api/cart")
public class CartRestController {
    private final CartService service;

    @Autowired
    public CartRestController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody CartRawDTO dto,
                                       BindingResult validation){
        try {
            if(!validation.hasErrors()){
                this.service.save(dto);
                return ResponseEntity.status(HttpStatus.OK).body("Cart Saved");
            }else {
                var errorDto = this.service.getError(validation);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDto);
            }
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Runtime Error");
        }
    }
}


package com.trollMarket.service;

import com.trollMarket.dto.Cart.CartErrorDTO;
import com.trollMarket.dto.Cart.CartIndexDTO;
import com.trollMarket.dto.Cart.CartRawDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CartService {
    List<CartIndexDTO> get(String username);
    void save(CartRawDTO dto);
    void remove(Integer id);
    Boolean purchaseAll(String username);
    String getStatusPurchase(List<CartIndexDTO> dto);
    CartErrorDTO getError(BindingResult validation);
}

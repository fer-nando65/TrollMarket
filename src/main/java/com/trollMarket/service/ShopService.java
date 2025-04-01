package com.trollMarket.service;

import com.trollMarket.dto.Shop.ShopIndexDTO;
import com.trollMarket.dto.Shop.ShopInfoDTO;

import java.util.List;

public interface ShopService {
    List<ShopIndexDTO> get(String merchandiseName, Integer categoryId, String description);
    ShopInfoDTO getInfo(Integer id);
}

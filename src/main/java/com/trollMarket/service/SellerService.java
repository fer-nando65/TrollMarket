package com.trollMarket.service;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.entity.Seller;

import java.util.List;

public interface SellerService {
    void save(Seller seller);
    List<OptionDTO> getOptionSeller(String username);
}

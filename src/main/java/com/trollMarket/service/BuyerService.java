package com.trollMarket.service;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.entity.Buyer;

import java.util.List;

public interface BuyerService {
    public void save(Buyer buyer);
    List<OptionDTO> getOptionBuyer(String username);
}

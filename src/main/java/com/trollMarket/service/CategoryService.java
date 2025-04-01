package com.trollMarket.service;

import com.trollMarket.dto.Option.OptionDTO;

import java.util.List;

public interface CategoryService {
    List<OptionDTO> get(Integer id);
}

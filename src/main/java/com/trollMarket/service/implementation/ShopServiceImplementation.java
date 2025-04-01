package com.trollMarket.service.implementation;

import com.trollMarket.dto.Shop.ShopIndexDTO;
import com.trollMarket.dto.Shop.ShopInfoDTO;
import com.trollMarket.helper.CustomFormat;
import com.trollMarket.repository.CategoryRepository;
import com.trollMarket.repository.MerchandiseRepository;
import com.trollMarket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShopServiceImplementation implements ShopService {
    private final MerchandiseRepository merchandiseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ShopServiceImplementation(MerchandiseRepository merchandiseRepository, CategoryRepository categoryRepository) {
        this.merchandiseRepository = merchandiseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ShopIndexDTO> get(String merchandiseName, Integer categoryId, String description){
        List<Integer> listCategoryId = new LinkedList<>();
        if(categoryId != 0){
            listCategoryId.add(categoryId);
        }else {
            listCategoryId = this.categoryRepository.getOnlyId();
        }

        var merchandises = this.merchandiseRepository.get(merchandiseName, listCategoryId, description);
        List<ShopIndexDTO> dto = new LinkedList<>();
        for(var mer : merchandises){
            var shopDto = new ShopIndexDTO();
            shopDto.setMerchandiseId(mer.getId());
            shopDto.setMerchandiseName(mer.getName());
            shopDto.setPrice(CustomFormat.currencyIndoFormat(mer.getPrice()));
            dto.add(shopDto);
        }
        return dto;
    }

    @Override
    public ShopInfoDTO getInfo(Integer id){
        var merchandise = this.merchandiseRepository.getById(id);
        var info = new ShopInfoDTO();
        info.setMerchandiseName(merchandise.getName());
        info.setCategoryName(merchandise.getCategory().getName());
        info.setDescription(merchandise.getDescription());
        info.setPrice(CustomFormat.currencyIndoFormat(merchandise.getPrice()));
        info.setSellerName(merchandise.getSeller().getProfile().getName());

        return info;
    }
}

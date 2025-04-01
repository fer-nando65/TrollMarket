package com.trollMarket.service.implementation;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.entity.Seller;
import com.trollMarket.repository.SellerRepository;
import com.trollMarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SellerServiceImplementation implements SellerService {
    private final SellerRepository repository;

    @Autowired
    public SellerServiceImplementation(SellerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Seller seller){
        this.repository.save(seller);
    }

    @Override
    public List<OptionDTO> getOptionSeller(String username){
        var listSeller = this.repository.findAll();
        List<OptionDTO> optionSeller = new LinkedList<>();
        for(var seller : listSeller){
            boolean selected = false;
            if(seller.getUsername().equals(username)){
                selected = true;
            }
            var opt = new OptionDTO(seller.getProfile().getName(), seller.getUsername(), selected);
            optionSeller.add(opt);
        }
        return optionSeller;
    }
}

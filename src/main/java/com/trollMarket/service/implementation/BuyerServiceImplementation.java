package com.trollMarket.service.implementation;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.entity.Buyer;
import com.trollMarket.repository.BuyerRepository;
import com.trollMarket.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BuyerServiceImplementation implements BuyerService {
    private final BuyerRepository repository;

    @Autowired
    public BuyerServiceImplementation(BuyerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Buyer buyer){
        this.repository.save(buyer);
    }

    @Override
    public List<OptionDTO> getOptionBuyer(String username){
        var listBuyer = this.repository.findAll();
        List<OptionDTO> optionBuyer = new LinkedList<>();
        for(var buyer : listBuyer){
            boolean selected = false;
            if(buyer.getUsername().equals(username)){
                selected = true;
            }
            var opt = new OptionDTO(buyer.getProfile().getName(), buyer.getUsername(), selected);
            optionBuyer.add(opt);
        }
        return optionBuyer;
    }
}

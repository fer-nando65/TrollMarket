package com.trollMarket.service.implementation;

import com.trollMarket.dto.Merchandise.MerchandiseIndexDTO;
import com.trollMarket.dto.Merchandise.MerchandiseInfoDTO;
import com.trollMarket.dto.Merchandise.MerchandiseUpsertDTO;
import com.trollMarket.entity.Merchandise;
import com.trollMarket.helper.CustomFormat;
import com.trollMarket.repository.CartRepository;
import com.trollMarket.repository.MerchandiseRepository;
import com.trollMarket.repository.OrderDetailRepository;
import com.trollMarket.service.CategoryService;
import com.trollMarket.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MerchandiseServiceImplementation implements MerchandiseService {
    private final MerchandiseRepository repository;
    private final CategoryService categoryService;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;

    @Autowired
    public MerchandiseServiceImplementation(MerchandiseRepository repository, CategoryService categoryService, OrderDetailRepository orderDetailRepository, CartRepository cartRepository) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<MerchandiseIndexDTO> get(String username){
        List<Merchandise> merchandises = this.repository.get(username);
        List<MerchandiseIndexDTO> dto = new LinkedList<>();

        for(var mer : merchandises){
            var merDto = new MerchandiseIndexDTO();
            merDto.setMerchandiseId(mer.getId());
            merDto.setMerchandiseName(mer.getName());
            merDto.setCategoryName(mer.getCategory().getName());
            merDto.setDiscontinue(mer.getDiscontinue() == true ? "Yes" : "No");
            int totalExists = 0;
            totalExists = totalExists + this.orderDetailRepository.countByMerchandise(mer.getId());
            totalExists = totalExists + this.cartRepository.countByMerchandise(mer.getId());

            if(totalExists != 0){
                merDto.setIsDeletable("no");
            }else {
                merDto.setIsDeletable("yes");
            }
            dto.add(merDto);
        }
        return dto;
    }

    @Override
    public MerchandiseUpsertDTO getMerchandise(Integer id, String username){
        var dto = new MerchandiseUpsertDTO();
        if(id != 0){
            var selectedMerchandise = this.repository.getById(id);
            dto.setMerchandiseId(selectedMerchandise.getId());
            dto.setMerchandiseName(selectedMerchandise.getName());
            dto.setCategoryId(selectedMerchandise.getCategoryId());
            dto.setDiscontinue(selectedMerchandise.getDiscontinue());
            dto.setDescription(selectedMerchandise.getDescription());
            dto.setPrice(selectedMerchandise.getPrice());
            dto.setListCategory(this.categoryService.get(selectedMerchandise.getCategoryId()));
            dto.setUsername(username);
        }else {
            dto.setUsername(username);
            dto.setListCategory(this.categoryService.get(-1));
        }

        return dto;
    }

    @Override
    public void save(MerchandiseUpsertDTO dto){
        var merchandise = new Merchandise();
        merchandise.setId(dto.getMerchandiseId());
        merchandise.setName(dto.getMerchandiseName());
        merchandise.setCategoryId(dto.getCategoryId());
        merchandise.setUsernameSeller(dto.getUsername());
        merchandise.setDescription(dto.getDescription());
        merchandise.setPrice(dto.getPrice());
        merchandise.setDiscontinue(dto.getDiscontinue() == null ? false : dto.getDiscontinue());

        this.repository.save(merchandise);
    }

    @Override
    public void setDiscontinue(Integer id){
        var merchandise = this.repository.getById(id);
        merchandise.setDiscontinue(true);
        this.repository.save(merchandise);
    }

    @Override
    public void delete(Integer id){
        var merchandise = this.repository.getById(id);
        this.repository.delete(merchandise);
    }

    @Override
    public MerchandiseInfoDTO getInfo(Integer id){
        var merchandise = this.repository.getById(id);
        var infoDto = new MerchandiseInfoDTO();
        infoDto.setMerchandiseName(merchandise.getName());
        infoDto.setCategoryName(merchandise.getCategory().getName());
        infoDto.setDescription(merchandise.getDescription());
        infoDto.setPrice(CustomFormat.currencyIndoFormat(merchandise.getPrice()));
        infoDto.setDiscontinue(merchandise.getDiscontinue() == true ? "Yes" : "No");
        return infoDto;
    }
}

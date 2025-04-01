package com.trollMarket.service.implementation;

import com.trollMarket.dto.Admin.AllHistoryDTO;
import com.trollMarket.helper.CustomFormat;
import com.trollMarket.repository.BuyerRepository;
import com.trollMarket.repository.OrderDetailRepository;
import com.trollMarket.repository.ProfileRepository;
import com.trollMarket.repository.SellerRepository;
import com.trollMarket.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProfileRepository profileRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    @Autowired
    public AdminServiceImplementation(OrderDetailRepository orderDetailRepository, ProfileRepository profileRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.profileRepository = profileRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public List<AllHistoryDTO> get(String sellerName, String buyerName){
        List<String> listSellerName = new LinkedList<>();
        List<String> listBuyerName = new LinkedList<>();


        if(sellerName.equals("")){
            for(var seller : this.sellerRepository.findAll()){
                listSellerName.add(seller.getUsername());
            }
        }else {
            listSellerName.add(sellerName);
        }

        if(buyerName.equals("")){
            for(var buyer : this.buyerRepository.findAll()){
                listBuyerName.add(buyer.getUsername());
            }
        }else {
            listBuyerName.add(buyerName);
        }



        var AllHistoryRaw = this.orderDetailRepository.getAllHistory(listSellerName, listBuyerName);
        List<AllHistoryDTO> dto = new LinkedList<>();
        for(var data : AllHistoryRaw){
            var history = new AllHistoryDTO();
            history.setDate(CustomFormat.dateFormatter(data.getOrderDate()));
            history.setSellerName(this.profileRepository.getById(data.getSellerName()).getName());
            history.setBuyerName(this.profileRepository.getById(data.getBuyerName()).getName());
            history.setMerchandiseName(data.getMerchandiseName());
            history.setQuantity(data.getQuantity());
            history.setShipperName(data.getShipperName());
            String totalPrice = CustomFormat.currencyIndoFormat((data.getPrice().multiply(new BigDecimal(data.getQuantity()))).add(data.getDeliveryCost()));
            history.setTotalPrice(totalPrice);

            dto.add(history);
        }
        return dto;
    }
}

package com.trollMarket.service.implementation;

import com.trollMarket.dto.Profile.ProfileAddBalanceDTO;
import com.trollMarket.dto.Profile.ProfileHistoryDTO;
import com.trollMarket.dto.Profile.ProfileHistoryRawDTO;
import com.trollMarket.dto.Profile.ProfileIndexDTO;
import com.trollMarket.entity.Profile;
import com.trollMarket.helper.CustomFormat;
import com.trollMarket.repository.*;
import com.trollMarket.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileServiceImplementation implements ProfileService {
    private final ProfileRepository repository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ProfileServiceImplementation(ProfileRepository repository, BuyerRepository buyerRepository, SellerRepository sellerRepository, OrderDetailRepository orderDetailRepository, AccountRepository accountRepository) {
        this.repository = repository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ProfileIndexDTO getProfile(String username){
        var dto = new ProfileIndexDTO();
        var selectedProfile = this.repository.getById(username);
        if(selectedProfile.getAccount().getRole().equals("Buyer")){
            var buyer = this.buyerRepository.getById(username);
            dto.setName(selectedProfile.getName());
            dto.setRole(selectedProfile.getAccount().getRole());
            dto.setAddress(selectedProfile.getAddress());
            dto.setBalance(CustomFormat.currencyIndoFormat(buyer.getBalance()));
        }else if(selectedProfile.getAccount().getRole().equals("Seller")){
            var seller = this.sellerRepository.getById(username);

            dto.setName(selectedProfile.getName());
            dto.setRole(selectedProfile.getAccount().getRole());
            dto.setAddress(selectedProfile.getAddress());
            dto.setBalance(CustomFormat.currencyIndoFormat(seller.getBalance()));
            var halfBalance = seller.getBalance().multiply(new BigDecimal(50).divide(new BigDecimal(100)));
            dto.setHalfBalance(halfBalance.intValue());
            dto.setFullBalance(seller.getBalance().intValue());
        }

        return dto;
    }

    @Override
    public void addBalance(ProfileAddBalanceDTO dto){
        var buyer = this.buyerRepository.getById(dto.getUsername());
        var balance = buyer.getBalance().add(dto.getBalance());
        buyer.setBalance(balance);
        this.buyerRepository.save(buyer);
    }

    @Override
    public void save(Profile profile){
        this.repository.save(profile);
    }

    @Override
    public List<ProfileHistoryDTO> getHistoryProfile(String username){
        var account = this.accountRepository.findById(username).orElseThrow();

        List<ProfileHistoryRawDTO> listProfileHistoryRaw = new LinkedList<>();
        if(account.getRole().equals("Buyer")){
            listProfileHistoryRaw = this.orderDetailRepository.getHistoryBuyer(username);
        }else if(account.getRole().equals("Seller")){
            listProfileHistoryRaw = this.orderDetailRepository.getHistorySeller(username);
        }

        List<ProfileHistoryDTO> listProfileHistory = new LinkedList<>();
        for(var data : listProfileHistoryRaw){
            var history = new ProfileHistoryDTO();
            history.setDate(CustomFormat.dateFormatter(data.getDate()));
            history.setMerchandiseName(data.getMerchandiseName());
            history.setQuantity(data.getQuantity());
            history.setShipperName(data.getShipperName());
            history.setTotalPrice(CustomFormat.currencyIndoFormat((data.getPrice().multiply(new BigDecimal(data.getQuantity()))).add(data.getDeliveryCost())));

            listProfileHistory.add(history);
        }
        return listProfileHistory;
    }

    @Override
    public void getBalance(String username, Integer amountMoney){
        var user = this.sellerRepository.findById(username).orElseThrow();
        var currentBalance = user.getBalance();
        currentBalance = currentBalance.subtract(new BigDecimal(amountMoney));
        user.setBalance(currentBalance);

        this.sellerRepository.save(user);
    }
}

package com.trollMarket.service;

import com.trollMarket.dto.Profile.ProfileAddBalanceDTO;
import com.trollMarket.dto.Profile.ProfileHistoryDTO;
import com.trollMarket.dto.Profile.ProfileIndexDTO;
import com.trollMarket.entity.Profile;

import java.util.List;

public interface ProfileService {
    ProfileIndexDTO getProfile(String username);
    void addBalance(ProfileAddBalanceDTO dto);
    void save(Profile profile);
    List<ProfileHistoryDTO> getHistoryProfile(String username);
    void getBalance(String username, Integer amountMoney);
}

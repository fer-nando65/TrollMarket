package com.trollMarket.service;

import com.trollMarket.dto.Admin.AllHistoryDTO;

import java.util.List;

public interface AdminService {
    List<AllHistoryDTO> get(String sellerName, String buyerName);
}

package com.trollMarket.controller;

import com.trollMarket.service.AdminService;
import com.trollMarket.service.BuyerService;
import com.trollMarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("history")
public class HistoryController {
    private final AdminService service;
    private final BuyerService buyerService;
    private final SellerService sellerService;

    @Autowired
    public HistoryController(AdminService service, BuyerService buyerService, SellerService sellerService) {
        this.service = service;
        this.buyerService = buyerService;
        this.sellerService = sellerService;
    }

    @GetMapping("index")
    public String AllHistory(@RequestParam(defaultValue = "")String sellerName,
                             @RequestParam(defaultValue = "")String buyerName,
                             Model model){
        model.addAttribute("gridAllHistory", this.service.get(sellerName, buyerName));
        model.addAttribute("optionBuyer", this.buyerService.getOptionBuyer(buyerName));
        model.addAttribute("optionSeller", this.sellerService.getOptionSeller(sellerName));
        return "history/history-index";
    }
}

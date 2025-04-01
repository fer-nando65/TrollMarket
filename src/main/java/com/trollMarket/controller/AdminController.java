package com.trollMarket.controller;

import com.trollMarket.dto.Account.AccountAdminRegisterDTO;
import com.trollMarket.service.AccountService;
import com.trollMarket.service.AdminService;
import com.trollMarket.service.BuyerService;
import com.trollMarket.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final AccountService accountService;


    @Autowired
    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("adminRegisterDto", new AccountAdminRegisterDTO());
        return "admin/admin-form";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("adminRegisterDto") AccountAdminRegisterDTO dto,
                           BindingResult validation){
        if(validation.hasErrors()){
            return "admin/admin-form";
        }else {
            this.accountService.registerAdmin(dto);
            return "redirect:/admin/index";
        }
    }
}

package com.trollMarket.controller;

import com.trollMarket.dto.Account.AccountLoginDTO;
import com.trollMarket.dto.Account.AccountRegisterDTO;
import com.trollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("account")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("login")
    public String login(@Valid @ModelAttribute("loginDto") @RequestParam(required = false) boolean error, Model model){
        model.addAttribute("error", error);
        model.addAttribute("loginDto", new AccountLoginDTO());
        return "/account/login-form";
    }

    @GetMapping("register")
    public String register(@RequestParam String role, Model model){
        model.addAttribute("roleUser", role);
        model.addAttribute("registerDto", new AccountRegisterDTO());
        return "account/register-form";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("registerDto") AccountRegisterDTO dto,
                           BindingResult validation,
                           Model model){
        if(validation.hasErrors()){
            model.addAttribute("roleUser", dto.getRole());
            return "account/register-form";
        }else {
            this.service.register(dto);
            return "redirect:/account/login";
        }
    }

    @GetMapping("accessDenied")
    public String accessDenied(){
        return "account/access-denied";
    }

    @GetMapping("failLogin")
    public String failLogin(){
        return "account/fail-login";
    }
}

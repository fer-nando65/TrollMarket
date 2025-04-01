package com.trollMarket.controller;

import com.trollMarket.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("index")
    public String index(@RequestParam String username,
                        Model model){
        var profile = this.service.getProfile(username);
        model.addAttribute("userProfile", profile);
        model.addAttribute("roleType", profile.getRole());
        model.addAttribute("username", username);
        model.addAttribute("listProfileHistory", this.service.getHistoryProfile(username));
        return "profile/profile-index";
    }

    @GetMapping("getBalance")
    public String getBalance(@RequestParam String username,
                             @RequestParam Integer amountMoney){
        this.service.getBalance(username, amountMoney);
        return String.format("redirect:/profile/index?username=%s", username);
    }
}

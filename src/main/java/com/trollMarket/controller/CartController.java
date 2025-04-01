package com.trollMarket.controller;

import com.trollMarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {
    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("index")
    public String index(@RequestParam String username, Model model){
        var gridCart = this.service.get(username);
        model.addAttribute("gridCart", gridCart);
        model.addAttribute("statusCart", this.service.getStatusPurchase(gridCart));
        return "cart/cart-index";
    }

    @GetMapping("remove")
    public String remove(@RequestParam String username,
                         @RequestParam Integer id){
        this.service.remove(id);
        return String.format("redirect:/cart/index?username=%s", username);
    }

    @GetMapping("purchaseAll")
    public String purchaseAll(@RequestParam String username){
        Boolean isPurchased = this.service.purchaseAll(username);
        if(isPurchased){
            return String.format("redirect:/cart/index?username=%s", username);
        }else {
            return "redirect:/cart/InsufficientBalance";
        }
    }

    @GetMapping("InsufficientBalance")
    public String InsufficientBalance(){
        return "cart/insufficient-balance";
    }
}

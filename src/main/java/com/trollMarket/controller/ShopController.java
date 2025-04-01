package com.trollMarket.controller;

import com.trollMarket.service.CategoryService;
import com.trollMarket.service.ShipperService;
import com.trollMarket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shop")
public class ShopController {
    private final ShopService service;
    private final CategoryService categoryService;
    private final ShipperService shipperService;

    @Autowired
    public ShopController(ShopService service, CategoryService categoryService, ShipperService shipperService) {
        this.service = service;
        this.categoryService = categoryService;
        this.shipperService = shipperService;
    }

    @GetMapping("index")
    public String index(@RequestParam String username,
                        @RequestParam(defaultValue = "")String merchandiseName,
                        @RequestParam(defaultValue = "0") Integer categoryId,
                        @RequestParam(defaultValue = "") String description,
                        Model model){
        model.addAttribute("gridListShop", this.service.get(merchandiseName, categoryId, description));
        model.addAttribute("listCategoryShop", categoryService.get(categoryId));
        model.addAttribute("listShipperShop", this.shipperService.getOptionShipper());
        return "shop/shop-index";
    }
}

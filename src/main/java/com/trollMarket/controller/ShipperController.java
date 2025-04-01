package com.trollMarket.controller;

import com.trollMarket.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shipper")
public class ShipperController {
    private final ShipperService service;

    @Autowired
    public ShipperController(ShipperService service) {
        this.service = service;
    }

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("gridShipper", this.service.get());
        return "shipment/shipment-index";
    }

    @GetMapping("delete")
    public String delete(@RequestParam Integer id){
        this.service.delete(id);
        return "redirect:/shipper/index";
    }

    @GetMapping("stopService")
    public String stopService(@RequestParam Integer id){
        this.service.stopService(id);
        return "redirect:/shipper/index";
    }
}

package com.trollMarket.rest;

import com.trollMarket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shop")
public class ShopRestController {
    private final ShopService service;

    @Autowired
    public ShopRestController(ShopService service) {
        this.service = service;
    }

    @GetMapping("/info")
    public ResponseEntity<Object> getInfo(@RequestParam Integer id){
        try {
            var detailInfo = this.service.getInfo(id);
            return ResponseEntity.status(HttpStatus.OK).body(detailInfo);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Runtime Error");
        }
    }
}

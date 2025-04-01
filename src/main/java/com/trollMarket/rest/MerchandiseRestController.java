package com.trollMarket.rest;

import com.trollMarket.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/merchandise")
public class MerchandiseRestController {
    private final MerchandiseService service;

    @Autowired
    public MerchandiseRestController(MerchandiseService service) {
        this.service = service;
    }

    @GetMapping("info")
    public ResponseEntity<Object> getInfo(@RequestParam Integer id){
        try{
            var info = this.service.getInfo(id);
            return ResponseEntity.status(HttpStatus.OK).body(info);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Runtime Error");
        }
    }
}

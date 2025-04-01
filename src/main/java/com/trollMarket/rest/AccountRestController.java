package com.trollMarket.rest;

import com.trollMarket.dto.Account.AccountLoginDTO;
import com.trollMarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
    private final AccountService service;

    @Autowired
    public AccountRestController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> createToken(@RequestBody AccountLoginDTO dto){
        try {
            var token = this.service.createToken(dto);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<Object> addAdmin(){
        var username = "Admin";
        var password = "123";

        try {
            this.service.registerAdmin(username, password);
            return ResponseEntity.status(HttpStatus.OK).body("Pembuatan akun admin berhasil");
        } catch (Exception  exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal buat akun admin");
        }
    }
}

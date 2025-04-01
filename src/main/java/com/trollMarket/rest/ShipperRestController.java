package com.trollMarket.rest;

import com.trollMarket.dto.Shipper.ShipperUpsertDTO;
import com.trollMarket.service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8085")
@RestController
@RequestMapping("api/shipper")
public class ShipperRestController {
    private final ShipperService service;

    @Autowired
    public ShipperRestController(ShipperService service) {
        this.service = service;
    }

    @GetMapping("detail")
    public ResponseEntity<Object>get(@RequestParam Integer id){
        try {
            var detail = this.service.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(detail);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Runtime Error");
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody ShipperUpsertDTO dto,
                                       BindingResult validation){
        try {
            if(!validation.hasErrors()){
                this.service.save(dto);
                return ResponseEntity.status(HttpStatus.OK).body("Shipper Saved");
            }else {
                var errorDto = this.service.getError(validation);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDto);
            }
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Runtime Error");
        }
    }
}

package com.trollMarket.dto.Shipper;

import com.trollMarket.validation.UniqueShipperCompanyName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShipperUpsertDTO {
    private Integer shipperId;

    @NotBlank
    @UniqueShipperCompanyName(message = "Shipper name is already exists!")
    private String companyName;

    @NotNull(message = "Price should not be empty!")
    @Min(value = 1, message = "Price is invalid!")
    private BigDecimal price;

    private Boolean service;
}

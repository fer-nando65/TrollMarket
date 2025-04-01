package com.trollMarket.dto.Shipper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShipperIndexDTO {
    private Integer shipperId;
    private String companyName;
    private String price;
    private String service;
    private String isDeletable;
}

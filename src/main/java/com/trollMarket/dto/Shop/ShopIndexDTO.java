package com.trollMarket.dto.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopIndexDTO {
    private Integer merchandiseId;
    private String merchandiseName;
    private String price;
}

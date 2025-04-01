package com.trollMarket.dto.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopInfoDTO {
    private String merchandiseName;
    private String categoryName;
    private String description;
    private String price;
    private String sellerName;
}

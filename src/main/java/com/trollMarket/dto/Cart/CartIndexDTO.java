package com.trollMarket.dto.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartIndexDTO {
    private Integer cartId;
    private String merchandiseName;
    private Integer quantity;
    private String shipperName;
    private String sellerName;
    private String totalPrice;
}

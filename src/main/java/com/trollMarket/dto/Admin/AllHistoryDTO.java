package com.trollMarket.dto.Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllHistoryDTO {
    private String date;
    private String sellerName;
    private String buyerName;
    private String merchandiseName;
    private Integer quantity;
    private String shipperName;
    private String totalPrice;
}

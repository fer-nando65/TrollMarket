package com.trollMarket.dto.Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllHistoryRawDTO {
    private LocalDate orderDate;
    private String sellerName;
    private String buyerName;
    private String merchandiseName;
    private Integer quantity;
    private String shipperName;
    private BigDecimal price;
    private BigDecimal deliveryCost;
}

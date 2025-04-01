package com.trollMarket.dto.Profile;

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
public class ProfileHistoryRawDTO {
    private LocalDate date;
    private String merchandiseName;
    private Integer quantity;
    private String shipperName;
    private BigDecimal price;
    private BigDecimal deliveryCost;
}

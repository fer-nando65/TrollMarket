package com.trollMarket.dto.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileHistoryDTO {
    private String date;
    private String merchandiseName;
    private Integer quantity;
    private String shipperName;
    private String totalPrice;
}

package com.trollMarket.dto.Cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRawDTO {
    private String username;
    private Integer merchandiseId;

    @NotNull(message = "Amount of item should not be empty!")
    @Min(value = 1, message = "Amount of item is invalid!")
    private Integer amount;

    @NotNull(message = "Shipper should not be empty!")
    private Integer shipperId;
}

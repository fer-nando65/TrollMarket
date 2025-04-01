package com.trollMarket.dto.Profile;

import jakarta.validation.constraints.Min;
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
public class ProfileAddBalanceDTO {
    private String username;

    @NotNull(message = "Balance should not be empty!")
    @Min(value = 1, message = "Balance is invalid!")
    private BigDecimal balance;
}

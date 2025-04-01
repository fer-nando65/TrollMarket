package com.trollMarket.dto.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileIndexDTO {
    private String name;
    private String role;
    private String address;
    private String balance;
    private Integer halfBalance;
    private Integer fullBalance;
}

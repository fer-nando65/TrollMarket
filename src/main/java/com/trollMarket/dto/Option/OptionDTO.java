package com.trollMarket.dto.Option;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionDTO {
    private String text;
    private String value;
    private Boolean selected;
}

package com.trollMarket.dto.Merchandise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MerchandiseIndexDTO {
    private Integer merchandiseId;
    private String merchandiseName;
    private String categoryName;
    private String discontinue;
    private String isDeletable;
}

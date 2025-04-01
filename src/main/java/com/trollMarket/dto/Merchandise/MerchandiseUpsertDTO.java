package com.trollMarket.dto.Merchandise;

import com.trollMarket.dto.Option.OptionDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MerchandiseUpsertDTO {
    private Integer merchandiseId;

    @NotBlank(message = "Merchandise name should not be empty!")
    private String merchandiseName;

    @NotNull(message = "No Specific Category!")
    private Integer categoryId;

    @NotBlank(message = "Description should not be empty!")
    private String description;

    @Min(value = 1, message = "Price is invalid!")
    @NotNull(message = "Price should not be empty!")
    private BigDecimal price;

    private Boolean discontinue;

    private String username;

    List<OptionDTO> listCategory;
}

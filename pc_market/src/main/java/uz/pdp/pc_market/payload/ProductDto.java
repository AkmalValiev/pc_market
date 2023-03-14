package uz.pdp.pc_market.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotNull(message = "categoryId null bo'masligi kerak!")
    private Integer categoryId;

    @NotNull(message = "guaranteeYear null bo'masligi kerak!")
    private Integer guaranteeYear;

    @NotNull(message = "description null bo'masligi kerak!")
    private String description;

    private Set<Integer> detailsId;


}

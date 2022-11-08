package com.fiba.shoppingservice.business.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductViewDto {
    private long cartProductId;
    private long productId;
    private int salesQuantity;
    private double salesPrice;
    private double lineAmount;
}

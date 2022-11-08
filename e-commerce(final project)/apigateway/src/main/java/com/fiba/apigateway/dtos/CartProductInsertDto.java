package com.fiba.apigateway.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductInsertDto {
    private long cartProductId;
    private long cartId;
    private long productId;
    private int salesQuantity;
    private double salesPrice;
    private double lineAmount;
}

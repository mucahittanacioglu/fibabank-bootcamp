package com.fiba.shoppingservice.business.dto;

import com.fiba.shoppingservice.data.entities.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
    private long cartProductId;
    private long cartId;
    private long productId;
    private int salesQuantity;
    private double salesPrice;
    private double lineAmount;
}

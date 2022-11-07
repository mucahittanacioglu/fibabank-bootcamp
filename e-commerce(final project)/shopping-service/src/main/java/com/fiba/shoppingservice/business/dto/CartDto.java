package com.fiba.shoppingservice.business.dto;

import com.fiba.shoppingservice.data.entities.CartProduct;
import lombok.*;


import java.util.List;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartDto {
    private long cartId;
    private String customerName;
    private List<CartProductDto> cartProducts;
}

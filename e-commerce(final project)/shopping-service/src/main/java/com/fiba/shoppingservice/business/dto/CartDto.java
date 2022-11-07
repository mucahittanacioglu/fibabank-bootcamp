package com.fiba.shoppingservice.business.dto;

import lombok.*;


import java.util.List;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartDto {
    private long cartId;
    private String customerName;
    private List<CartProductDto> cartProducts;
}

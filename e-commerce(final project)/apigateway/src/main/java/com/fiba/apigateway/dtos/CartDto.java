package com.fiba.apigateway.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private long cartId;
    private String customerName;
    private List<CartProductDto> cartProducts;
}
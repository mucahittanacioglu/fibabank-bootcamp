package com.fiba.inventoryservice.business.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductViewDto {
    private long productId;
    private String productName;
    private double salesPrice;
    private String categoryName;
}

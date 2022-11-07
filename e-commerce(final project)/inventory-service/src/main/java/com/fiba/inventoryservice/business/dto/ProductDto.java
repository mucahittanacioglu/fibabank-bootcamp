package com.fiba.inventoryservice.business.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductDto {
    private String productName;
    private double salesPrice;
    private String categoryName;

    public ProductDto(String productName, double salesPrice, String categoryName) {
        this.productName = productName;
        this.salesPrice = salesPrice;
        this.categoryName = categoryName;
    }
}

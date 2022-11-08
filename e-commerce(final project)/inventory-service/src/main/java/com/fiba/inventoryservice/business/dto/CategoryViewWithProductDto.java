package com.fiba.inventoryservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryViewWithProductDto {
    private Long categoryId;
    private String categoryName;
    private List<ProductViewDto> products;
}
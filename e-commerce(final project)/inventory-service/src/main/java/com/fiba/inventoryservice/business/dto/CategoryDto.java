package com.fiba.inventoryservice.business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CategoryDto {
    private String categoryName;

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }


}

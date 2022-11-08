package com.fiba.inventoryservice.business;

import com.fiba.inventoryservice.business.dto.CategoryInsertionDto;
import com.fiba.inventoryservice.business.dto.CategoryViewDto;
import com.fiba.inventoryservice.business.dto.ProductInsertionDto;
import com.fiba.inventoryservice.business.dto.ProductViewDto;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;

public abstract class Mapper {
    public static ProductViewDto productEntityToViewDto(Product product){
        return new ProductViewDto(product.getProductId(),product.getProductName(),product.getSalesPrice(),product.getCategory().getCategoryName());
    }
    public static Product productViewDtoToEntity(ProductViewDto productViewDto){
        return new Product(productViewDto.getProductId(),productViewDto.getProductName(),productViewDto.getSalesPrice());
    }
    public static Product productInsertionDtoToEntity(ProductInsertionDto productInsertionDto){
        return new Product(productInsertionDto.getProductName(),productInsertionDto.getSalesPrice());
    }
    public static CategoryViewDto categoryEntityToViewDto(Category category){

        return new CategoryViewDto(category.getCategoryId(),category.getCategoryName());
    }

    public static Category categoryInsertionDtoToEntity(CategoryInsertionDto categoryInsertionDto) {
        return new Category(categoryInsertionDto.getCategoryName());
    }
}

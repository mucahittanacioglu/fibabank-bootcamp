package com.fiba.inventoryservice.business;

import com.fiba.inventoryservice.business.dto.*;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;

import java.util.ArrayList;

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
    public static CategoryViewWithProductDto categoryEntityToViewWithProductDto(Category category){
        CategoryViewWithProductDto dto = new CategoryViewWithProductDto(category.getCategoryId(),category.getCategoryName(),new ArrayList<ProductViewDto>());
        category.getProductList().forEach(product -> dto.getProducts()
                .add(productEntityToViewDto(product)));
        return dto;
    }

    public static Category categoryInsertionDtoToEntity(CategoryInsertionDto categoryInsertionDto) {
        return new Category(categoryInsertionDto.getCategoryName());
    }
}

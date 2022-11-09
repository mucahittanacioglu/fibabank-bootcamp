package com.fiba.inventoryservice.business;

import com.fiba.inventoryservice.business.dto.*;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;

import java.util.ArrayList;

public abstract class Mapper {
    /**
     Maps given Product entity to Product view dto.
     @return ProductViewDto
     @param product Product entity.
     */
    public static ProductViewDto productEntityToViewDto(Product product){
        return new ProductViewDto(product.getProductId(),product.getProductName(),product.getSalesPrice(),product.getCategory().getCategoryName());
    }

    /**
     Maps given Product view dto to Product entity.
     @return Product
     @param productViewDto Product view dto.
     */
    public static Product productViewDtoToEntity(ProductViewDto productViewDto){
        return new Product(productViewDto.getProductId(),productViewDto.getProductName(),productViewDto.getSalesPrice());
    }

    /**
     Maps given Product insertion dto(comes from client) to Product entity.
     @return Product
     @param productInsertionDto Product that needs to be added into database.
     */
    public static Product productInsertionDtoToEntity(ProductInsertionDto productInsertionDto){
        return new Product(productInsertionDto.getProductName(),productInsertionDto.getSalesPrice());
    }
    /**
     Maps given Category entity to Category view dto.
     @return CategoryViewDto
     @param category
     */
    public static CategoryViewDto categoryEntityToViewDto(Category category){
        return new CategoryViewDto(category.getCategoryId(),category.getCategoryName());
    }

    /**
     Maps given Category entity to Category view dto with all products inside.
     @return CategoryViewWithProductDto
     @param category
     */
    public static CategoryViewWithProductDto categoryEntityToViewWithProductDto(Category category){
        CategoryViewWithProductDto dto = new CategoryViewWithProductDto(category.getCategoryId(),category.getCategoryName(),new ArrayList<ProductViewDto>());
        category.getProductList().forEach(product -> dto.getProducts()
                .add(productEntityToViewDto(product)));
        return dto;
    }

    /**
     Maps given Category insertion dto(comes from client) to Category entity.
     @return CategoryViewWithProductDto
     @param categoryInsertionDto
     */
    public static Category categoryInsertionDtoToEntity(CategoryInsertionDto categoryInsertionDto) {
        return new Category(categoryInsertionDto.getCategoryName());
    }
}

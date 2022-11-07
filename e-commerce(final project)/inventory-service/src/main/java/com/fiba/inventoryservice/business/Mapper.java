package com.fiba.inventoryservice.business;

import com.fiba.inventoryservice.business.dto.CategoryDto;
import com.fiba.inventoryservice.business.dto.ProductDto;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;

public abstract class Mapper {
    public static ProductDto productEntityToDto(Product product){
        return new ProductDto(product.getProductName(),product.getSalesPrice(),product.getCategory().getCategoryName());
    }
    public static Product productDtoToEntity(ProductDto productDto){
        return new Product(productDto.getProductName(),productDto.getSalesPrice());
    }

    public static CategoryDto categoryEntityToDto(Category category){

        return new CategoryDto(category.getCategoryName());
    }
    public static Category categoryDtoToEntity(CategoryDto categoryDto){
        return new Category(categoryDto.getCategoryName());
    }
}

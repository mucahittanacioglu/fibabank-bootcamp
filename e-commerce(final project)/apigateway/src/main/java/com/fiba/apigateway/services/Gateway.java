package com.fiba.apigateway.services;

import com.fiba.apigateway.dtos.CategoryDto;
import com.fiba.apigateway.dtos.ProductDto;
import com.fiba.apigateway.services.components.InventoryComponent;
import com.fiba.apigateway.services.components.ShoppingComponent;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Gateway {
    @Autowired
    InventoryComponent _inventoryComponent;
    @Autowired
    ShoppingComponent _shoppingComponent;

    public DataResult<List<CategoryDto>> getAlCategories(){
        return _inventoryComponent.getAllCategories();
    }
    public DataResult<ProductDto> getProductById(long productId){
        return _inventoryComponent.getProductWithId(productId);
    }
    public DataResult<List<ProductDto>> getProductByCategory(long categoryId){
        return _inventoryComponent.getProductsByCategoryId(categoryId);
    }
}

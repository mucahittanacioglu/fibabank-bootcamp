package com.fiba.inventoryservice.presentation.rest;

import com.fiba.inventoryservice.business.dto.ProductDto;
import com.fiba.inventoryservice.business.services.concretes.ProductManager;
import com.fiba.inventoryservice.data.entities.Product;
import com.fiba.inventoryservice.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/inventory")
public class ProductController {
    @Autowired
    private ProductManager _productManager;

    @GetMapping("/product/{productId}")
    public DataResult<ProductDto> getProductById(@PathVariable("productId") long productId){
        return _productManager.getProductWithId(productId);
    }
    @GetMapping("/products/{categoryId}")
    public DataResult<List<ProductDto>> getProductsByCategoryId(@PathVariable("categoryId") long categoryId){
        return _productManager.getProductsByCategoryId(categoryId);
    }


}

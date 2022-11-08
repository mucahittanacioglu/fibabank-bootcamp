package com.fiba.inventoryservice.presentation.rest;

import com.fiba.inventoryservice.business.dto.ProductInsertionDto;
import com.fiba.inventoryservice.business.dto.ProductViewDto;
import com.fiba.inventoryservice.business.services.concretes.CategoryManager;
import com.fiba.inventoryservice.business.services.concretes.ProductManager;
import com.fiba.inventoryservice.utilities.results.DataResult;
import com.fiba.inventoryservice.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/inventory")
public class ProductController {
    @Autowired
    private ProductManager _productManager;

    @GetMapping("/product/{productId}")
    public DataResult<ProductViewDto> getProductById(@PathVariable("productId") long productId){
        return _productManager.getProductWithId(productId);
    }
    @GetMapping("/products/{categoryId}")
    public DataResult<List<ProductViewDto>> getProductsByCategoryId(@PathVariable("categoryId") long categoryId){
        return _productManager.getProductsByCategoryId(categoryId);
    }
    @PostMapping("/product/add")
    public Result addProduct(@RequestBody ProductInsertionDto productInsertionDto){
        return _productManager.addProduct(productInsertionDto);
    }
    @DeleteMapping("/product/delete/{productId}")
    public Result deleteProductWithId(@PathVariable("productId") long productId){
        return _productManager.deleteProductWithId(productId);
    }

}

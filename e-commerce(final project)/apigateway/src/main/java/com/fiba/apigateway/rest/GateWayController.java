package com.fiba.apigateway.rest;

import com.fiba.apigateway.dtos.CategoryDto;
import com.fiba.apigateway.services.Gateway;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commerce")
public class GateWayController {
    @Autowired
    private Gateway _gateway;

    @GetMapping("/inventory/product/{productId}")
    public DataResult<?> getProductById(@PathVariable("productId") long productId){

        return _gateway.getProductById(productId);
    }
    @GetMapping("/inventory/products/{categoryId}")
    public DataResult<?> getProductsByCategoryId(@PathVariable("categoryId") long categoryId){

        return _gateway.getProductByCategory(categoryId);
    }
    @GetMapping("/inventory/categories")
    public DataResult<List<CategoryDto>> getAllCategories(){

        return _gateway.getAlCategories();
    }

    @GetMapping("/shopping/cart/create")
    public Result createCart(){
        return null;
    }
    @PostMapping("/shopping/cart/add")
    public Result addProductToCart(@RequestBody Object cartProductDto){
        return null;
    }
    @DeleteMapping("/cart/{cartId}/remove/{productId}")
    public Result deleteItemFromCart(@PathVariable("cartId")long cartId,@PathVariable("productId")long productId){
        return null;
    }
    @GetMapping("/cart/checkout/{cartId}")
    public Result checkout(@PathVariable("cartId")long cartId){
        return null;
    }
    @GetMapping("/cart/find/{cartId}")
    public Result find(@PathVariable("cartId")long cartId){

        return null;
    }
}

package com.fiba.apigateway.rest;

import com.fiba.apigateway.dtos.*;
import com.fiba.apigateway.services.Gateway;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public String getProductsByCategoryId(@PathVariable("categoryId") long categoryId){

        return _gateway.getProductByCategory(categoryId);
    }
    @GetMapping("/inventory/categories")
    public Result getAllCategories(){
        return _gateway.getAlCategories();
    }
    @PostMapping("/inventory/category/add")
    public Result createCategory(@RequestBody CategoryInsertionDto categoryInsertionDto){
        return _gateway.addCategory(categoryInsertionDto);
    }
    @DeleteMapping("/inventory/category/delete/{categoryId}")
    public Result deleteCategoryById(@PathVariable("categoryId") long categoryId){
        return _gateway.removeCategory(categoryId);
    }
    @PostMapping("/inventory/product/add")
    public Result addProduct(@RequestBody ProductInsertionDto productInsertionDto){
        return _gateway.addProduct(productInsertionDto);
    }
    @GetMapping("/inventory/category/{categoryId}")
    public String getCategoryWithData(@PathVariable("categoryId")long categoryId){
        return _gateway.getCategoryWithData(categoryId);
    }
    @DeleteMapping("/inventory/product/delete/{productId}")
    public Result deleteProductWithId(@PathVariable("productId") long productId){
        return _gateway.deleteProductWithId(productId);
    }

    @GetMapping("/shopping/cart/create")
    public String createCart(){
        return _gateway.createCart();
    }

    @GetMapping("/shopping/cart/create/{customerName}")
    public String createCart(@PathVariable("customerName") String customerName){ return _gateway.createCart(customerName); }

    @PostMapping("/shopping/cart/add")
    public Result addProductToCart(@RequestBody CartProductInsertDto cartProductInsertDto){
        return _gateway.addProductToCart(cartProductInsertDto);
    }

    @DeleteMapping("/shopping/cart/{cartId}/remove/{productId}")
    public Result deleteItemFromCart(@PathVariable("cartId")long cartId,@PathVariable("productId")long productId){
        return _gateway.deleteItemFromCart(cartId,productId);
    }

    @GetMapping("/shopping/cart/checkout/{cartId}")
    public Result checkout(@PathVariable("cartId")long cartId){
        return _gateway.checkout(cartId);
    }

    @GetMapping("/shopping/cart/find/{cartId}")
    public Result find(@PathVariable("cartId")long cartId){ return _gateway.findCartById(cartId);}


}

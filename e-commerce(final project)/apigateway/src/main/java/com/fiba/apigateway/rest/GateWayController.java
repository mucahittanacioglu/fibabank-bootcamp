package com.fiba.apigateway.rest;

import com.fiba.apigateway.services.Gateway;
import com.fiba.apigateway.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/commerce")
public class GateWayController {
    @Autowired
    private Gateway _gateway;
    @GetMapping("/inventory/product/{productId}")
    public DataResult<?> getProductById(@PathVariable("productId") long productId){
        return null;
    }
    @GetMapping("/inventory/products/{categoryId}")
    public DataResult<?> getProductsByCategoryId(@PathVariable("categoryId") long categoryId){
        return null;
    }
    @GetMapping("/categories")
    public DataResult<List<?>> getAllCategories(){
        return null;
    }

}

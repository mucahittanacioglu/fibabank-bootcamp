package com.fiba.shoppingservice.presentation.rest;

import com.fiba.shoppingservice.business.dto.CartProductDto;
import com.fiba.shoppingservice.business.services.concretes.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class CartController {
    @Autowired
    private CartManager _cartManager;

    @GetMapping("/cart/create")
    public long createCart(){
        return 0;
    }
    @PostMapping("cart/add")
    public void addProductToCart(CartProductDto cartProductDto){

    }
    @DeleteMapping("cart/{cartId}/remove/{productId}")
    public void deleteItemFromCart(@PathVariable("cartId")long cartId,@PathVariable("productId")long productId){

    }
    @GetMapping("/cart/checkout/{cartId}")
    public long checkout(@PathVariable("cartId")long cartId){
        return 0;
    }
    @GetMapping("/cart/checkout/find")
    public long find(){
        return 0;
    }
}

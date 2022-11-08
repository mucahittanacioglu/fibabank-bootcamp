package com.fiba.shoppingservice.presentation.rest;

import com.fiba.shoppingservice.business.dto.CartProductInsertionDto;
import com.fiba.shoppingservice.business.dto.CartProductViewDto;
import com.fiba.shoppingservice.business.services.concretes.CartManager;
import com.fiba.shoppingservice.business.services.concretes.CartProductManager;
import com.fiba.shoppingservice.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class CartController {
    @Autowired
    private CartManager _cartManager;
    @Autowired
    CartProductManager _cartProductManager;

    @GetMapping("/cart/create")
    public Result createCart(){

        return _cartManager.createNewCart();
    }
    @GetMapping("/cart/create/{customerName}")
    public Result createCart(@PathVariable("customerName")String customerName){
        return _cartManager.createNewCart(customerName);
    }
    @PostMapping("/cart/add")
    public Result addProductToCart(@RequestBody CartProductInsertionDto cartProductInsertionDto){
        return _cartManager.addItemToCart(cartProductInsertionDto);
    }
    @DeleteMapping("/cart/{cartId}/remove/{productId}")
    public Result deleteItemFromCart(@PathVariable("cartId")long cartId,@PathVariable("productId")long productId){
        Result result = _cartManager.removeItemFromCart(cartId,productId);
        /*if (result.isSuccess()){
            _cartProductManager.removeProductById(productId);
        }*/
        return result;
    }
    @GetMapping("/cart/checkout/{cartId}")
    public Result checkout(@PathVariable("cartId")long cartId){
        return _cartManager.cartCheckout(cartId);
    }
    @GetMapping("/cart/find/{cartId}")
    public Result find(@PathVariable("cartId")long cartId){

        return _cartManager.getCartById(cartId);
    }
}

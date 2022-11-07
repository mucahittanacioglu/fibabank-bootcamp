package com.fiba.shoppingservice.business;

import com.fiba.shoppingservice.business.dto.CartDto;
import com.fiba.shoppingservice.business.dto.CartProductDto;
import com.fiba.shoppingservice.data.entities.Cart;
import com.fiba.shoppingservice.data.entities.CartProduct;

import java.util.ArrayList;

public abstract class Mapper {

    public static CartDto cartEntityToDto(Cart cart){
        CartDto _result = new CartDto();
        _result.setCustomerName(cart.getCustomerName());
        _result.setCartId(cart.getCartId());
        _result.setCartProducts(new ArrayList<>());

        cart.getCartProducts()
                .forEach(cProduct -> _result.getCartProducts()
                .add(
                        cartProductEntityToDto(cProduct)
                ));
        return _result;
    }
    public static Cart cartDtoToEntity(CartDto cartDto){
        Cart _result = new Cart();
        _result.setCustomerName(cartDto.getCustomerName());
        _result.setCartId(cartDto.getCartId());
        cartDto.getCartProducts().forEach(cartProductDto ->
                _result.getCartProducts().add(
                        cartProductDtoToEntity(cartProductDto)
                ));
        return _result;
    }
    public static CartProductDto cartProductEntityToDto(CartProduct cartProduct){
        CartProductDto _result = new CartProductDto();
        _result.setCartProductId(cartProduct.getCartProductId());
        _result.setCartId(cartProduct.getCart().getCartId());
        _result.setProductId(cartProduct.getProductId());
        _result.setSalesPrice(cartProduct.getSalesPrice());
        _result.setSalesQuantity(cartProduct.getSalesQuantity());
        _result.setLineAmount(cartProduct.getLineAmount());

        return _result;
    }
    //set cart field on manager
    public static CartProduct cartProductDtoToEntity(CartProductDto cartProductDto){
        CartProduct _result = new CartProduct();
        _result.setCartProductId(cartProductDto.getCartProductId());
        _result.setProductId(cartProductDto.getProductId());
        _result.setSalesPrice(cartProductDto.getSalesPrice());
        _result.setSalesQuantity(cartProductDto.getSalesQuantity());
        _result.setLineAmount(cartProductDto.getLineAmount());
        return _result;
    }
}

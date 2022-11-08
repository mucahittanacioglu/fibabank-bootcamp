package com.fiba.shoppingservice.business;

import com.fiba.shoppingservice.business.dto.CartDto;
import com.fiba.shoppingservice.business.dto.CartProductInsertionDto;
import com.fiba.shoppingservice.business.dto.CartProductViewDto;
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
                        cartProductEntityToViewDto(cProduct)
                ));
        return _result;
    }
    public static Cart cartDtoToEntity(CartDto cartDto){
        Cart _result = new Cart();
        _result.setCustomerName(cartDto.getCustomerName());
        _result.setCartId(cartDto.getCartId());
        cartDto.getCartProducts().forEach(cartProductViewDto ->
                _result.getCartProducts().add(
                        cartProductViewDtoToEntity(cartProductViewDto)
                ));
        return _result;
    }
    public static CartProductViewDto cartProductEntityToViewDto(CartProduct cartProduct){
        CartProductViewDto _result = new CartProductViewDto();
        _result.setCartProductId(cartProduct.getCartProductId());
        //_result.setCartId(cartProduct.getCart().getCartId());
        _result.setProductId(cartProduct.getProductId());
        _result.setSalesPrice(cartProduct.getSalesPrice());
        _result.setSalesQuantity(cartProduct.getSalesQuantity());
        _result.setLineAmount(cartProduct.getLineAmount());

        return _result;
    }
    //set cart field on manager
    public static CartProduct cartProductViewDtoToEntity(CartProductViewDto cartProductViewDto){
        CartProduct _result = new CartProduct();
        _result.setCartProductId(-1);
        _result.setProductId(cartProductViewDto.getProductId());
        _result.setSalesPrice(cartProductViewDto.getSalesPrice());
        _result.setSalesQuantity(cartProductViewDto.getSalesQuantity());
        _result.setLineAmount(cartProductViewDto.getLineAmount());
        return _result;
    }

    public static CartProduct cartProductInsertionDtoToEntity(CartProductInsertionDto cartProductInsertionDto) {
        CartProduct _result = new CartProduct();
        _result.setCartProductId(-1);
        _result.setProductId(cartProductInsertionDto.getProductId());
        _result.setSalesPrice(cartProductInsertionDto.getSalesPrice());
        _result.setSalesQuantity(cartProductInsertionDto.getSalesQuantity());
        _result.setLineAmount(cartProductInsertionDto.getLineAmount());
        return _result;
    }
}

package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.Mapper;
import com.fiba.shoppingservice.business.dto.CartProductDto;
import com.fiba.shoppingservice.business.services.abstarcts.CartService;
import com.fiba.shoppingservice.data.entities.Cart;
import com.fiba.shoppingservice.data.entities.CartProduct;
import com.fiba.shoppingservice.data.repositories.CartRepository;
import com.fiba.shoppingservice.utilities.Messages.Messages;
import com.fiba.shoppingservice.utilities.results.DataResult;
import com.fiba.shoppingservice.utilities.results.Result;
import com.fiba.shoppingservice.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartManager implements CartService {

    @Autowired
    CartRepository _cart_repository;

    public Result createNewCart(){

        Cart cart= _cart_repository.save(new Cart("John Dao"));

        return new SuccessDataResult<>(cart.getCartId(), Messages.CART_CREATION_SUCCESS);
    }
    public Result addItemToCart(CartProductDto cartProductDto){
        CartProduct cartProduct = Mapper.cartProductDtoToEntity(cartProductDto);
        Optional<Cart> cartOptional = _cart_repository.findById(cartProductDto.getCartId());
        if (cartOptional.isPresent()){
            Cart cart = cartOptional.get();

        }
        return null;
    }
    public Result removeItemFromCart(){

        return null;
    }
    public Result cartCheckout(long cartId){
        return null;
    }
    public Result getCartById(long cartId){
        return null;
    }


}

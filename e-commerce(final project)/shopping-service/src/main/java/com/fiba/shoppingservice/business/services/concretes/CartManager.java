package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.Mapper;
import com.fiba.shoppingservice.business.dto.CartProductInsertionDto;
import com.fiba.shoppingservice.business.dto.CartProductViewDto;
import com.fiba.shoppingservice.business.services.abstarcts.CartService;
import com.fiba.shoppingservice.data.entities.Cart;
import com.fiba.shoppingservice.data.entities.CartProduct;
import com.fiba.shoppingservice.data.repositories.CartRepository;
import com.fiba.shoppingservice.utilities.Messages.Messages;
import com.fiba.shoppingservice.utilities.results.ErrorResult;
import com.fiba.shoppingservice.utilities.results.Result;
import com.fiba.shoppingservice.utilities.results.SuccessDataResult;
import com.fiba.shoppingservice.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CartManager implements CartService {

    @Autowired
    CartRepository _cartRepository;

    public Result createNewCart(){

        Cart cart= _cartRepository.save(new Cart("John Dao"));

        return new SuccessDataResult<>(cart.getCartId(), Messages.CART_CREATION_SUCCESS);
    }
    public Result createNewCart(String customerName){

        Cart cart= _cartRepository.save(new Cart(customerName));

        return new SuccessDataResult<>(cart.getCartId(), Messages.CART_CREATION_SUCCESS);
    }

    public Result addItemToCart(CartProductInsertionDto cartProductInsertionDto){

        CartProduct cartProduct = Mapper.cartProductInsertionDtoToEntity(cartProductInsertionDto);
        Optional<Cart> cartOptional = _cartRepository.findById(cartProductInsertionDto.getCartId());
        if (cartOptional.isPresent()){
            Cart cart = cartOptional.get();
            if (cart.getStatus()==1) return new ErrorResult(Messages.CART_CHECKED_OUT);
            //If product exist increment quantity
            Optional<CartProduct> tempProductOptional= cart.getCartProducts().stream().filter(cartProductS->
                    cartProductS.getProductId()==cartProduct.getProductId()).findFirst();
            if (tempProductOptional.isPresent()){
                CartProduct tempProduct = tempProductOptional.get();
                cart.getCartProducts().remove(tempProduct);
                tempProduct.setSalesQuantity(tempProduct.getSalesQuantity()+cartProduct.getSalesQuantity());
                tempProduct.updateLineAmount();
                cart.getCartProducts().add(tempProduct);

            }else{
                cartProduct.updateLineAmount();
                cart.getCartProducts().add(cartProduct);
            }
            cart.updateTotalAmount();
            _cartRepository.save(cart);

            return new SuccessResult(Messages.CART_PRODUCT_ADD_SUCCESS);
        }
        return new ErrorResult(Messages.CART_NOT_FOUND);
    }

    public Result removeItemFromCart(long cartId,long productId){
        Optional<Cart> cartOptional = _cartRepository.findById(cartId);
        if (cartOptional.isPresent()){
          Cart cart = cartOptional.get();
          Optional<CartProduct> productOptional = cart.getCartProducts().stream()
                  .filter(product -> product.getCartProductId() == productId).findFirst();
          if (productOptional.isPresent()){
              CartProduct cartProduct = productOptional.get();
              //remove completely if no quantity
              if (cartProduct.getSalesQuantity()==1){
                  cart.getCartProducts().remove(cartProduct);
                  cart.updateTotalAmount();
              }else{
                  cart.getCartProducts().remove(cartProduct);
                  cartProduct.setSalesQuantity(cartProduct.getSalesQuantity()-1);
                  cartProduct.updateLineAmount();
                  cart.getCartProducts().add(cartProduct);
              }

              Cart result = _cartRepository.save(cart);

              return new SuccessDataResult<>(result,Messages.CART_PRODUCT_DELETION_SUCCESS);
          }else{
              return new ErrorResult(Messages.CART_PRODUCT_NOT_EXIST);
          }
        }
        return new ErrorResult(Messages.CART_NOT_FOUND);
    }
    public Result cartCheckout(long cartId){
        Optional<Cart> cartOptional = _cartRepository.findById(cartId);
        if (cartOptional.isPresent()){
            Cart cart = cartOptional.get();
            if (cart.getStatus() == 1){
                return  new ErrorResult(Messages.CART_ALREADY_CHECKED);
            }
            cart.setStatus(1);
            _cartRepository.save(cart);
            return new SuccessResult(Messages.CART_CHECKOUT_SUCCESS);
        }
        return new ErrorResult(Messages.CART_CHECKOUT_FAILURE);
    }
    public Result getCartById(long cartId){
        Optional<Cart> cartOptional = _cartRepository.findById(cartId);
        if (cartOptional.isPresent()){
            return new SuccessDataResult<>(Mapper.cartEntityToDto(cartOptional.get()),Messages.CART_FOUND);
        }
        return new ErrorResult(Messages.CART_NOT_FOUND);
    }


}

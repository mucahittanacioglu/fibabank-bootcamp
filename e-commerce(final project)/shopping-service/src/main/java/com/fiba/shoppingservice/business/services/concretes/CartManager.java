package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.Mapper;
import com.fiba.shoppingservice.business.dto.CartProductInsertionDto;
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


import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartManager implements CartService {

    @Autowired
    CartRepository _cartRepository;

    public CartManager(CartRepository cartRepository) {
        this._cartRepository = cartRepository;
    }

    /**
     Create new cart in db with default name and return its id.
     @return SuccessDataResult with appropriate message.
     */
    public Result createNewCart(){

        Cart cart= _cartRepository.save(new Cart("John Dao"));

        return new SuccessDataResult<>(cart.getCartId(), Messages.CART_CREATION_SUCCESS);
    }

    /**
     Create new cart in db with given customer name,if its already exist return its id.
     @return SuccessDataResult with appropriate message.
     @param customerName cart owner.
     */
    public Result createNewCart(String customerName){
        Optional<Cart> existCartOptional = _cartRepository.findCartByName(customerName);
        //if cart with customer name exist return existing one else create new one
        if(existCartOptional.isPresent()){
            return new SuccessDataResult<>(existCartOptional.get().getCartId(), Messages.CART_ALREADY_EXIST);
        }
        Cart cartToAdd = new Cart(customerName);
        cartToAdd.setCartProducts(new ArrayList<CartProduct>());
        Cart cart= _cartRepository.save(cartToAdd);

        return new SuccessDataResult<>(cart.getCartId(), Messages.CART_CREATION_SUCCESS);
    }

    /**
     Maps given product dto to entity. If product exist in cart increment quantity otherwise create new one.
     @return SuccessResult,ErrorResult with appropriate message.
     @param cartProductInsertionDto product to add.
     */
    public Result addItemToCart(CartProductInsertionDto cartProductInsertionDto){

        CartProduct cartProduct = Mapper.cartProductInsertionDtoToEntity(cartProductInsertionDto);
        Optional<Cart> cartOptional = _cartRepository.findById(cartProductInsertionDto.getCartId());

        if (cartOptional.isPresent()){
            Cart cart = cartOptional.get();

            // if cart checked out return error
            if (cart.getStatus()==1) return new ErrorResult(Messages.CART_CHECKED_OUT);

            Optional<CartProduct> tempProductOptional= cart.getCartProducts().stream().filter(cartProductS->
                    cartProductS.getProductId()==cartProduct.getProductId()).findFirst();

            //if product already in cart increment quantity else add new one
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

    /**
     Delete product with given id from cart with given id.
     If product quantity more than 1 then it decremented by 1, otherwise deleted completely.
     @return SuccessDataResult,ErrorResult with appropriate message.
     @param cartId,productId
     */
    public Result removeItemFromCart(long cartId,long cartProductId){
        Optional<Cart> cartOptional = _cartRepository.findById(cartId);

        if (cartOptional.isPresent()){
          Cart cart = cartOptional.get();

          // if cart checked out return error
          if(cart.getStatus() == 1) return new ErrorResult(Messages.CART_CHECKED_OUT);

          Optional<CartProduct> productOptional = cart.getCartProducts().stream()
                  .filter(product -> product.getCartProductId() == cartProductId).findFirst();

          if (productOptional.isPresent()){
              CartProduct cartProduct = productOptional.get();

              //remove completely if no quantity else decrement quantity by 1
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

    /**
     Checkout cart if not.
     @return SuccessResult,ErrorResult with appropriate message.
     @param cartId cart id to check out.
     */
    public Result cartCheckout(long cartId){
        Optional<Cart> cartOptional = _cartRepository.findById(cartId);
        if (cartOptional.isPresent()){
            Cart cart = cartOptional.get();

            // if cart already checked out return error
            if (cart.getStatus() == 1) return  new ErrorResult(Messages.CART_ALREADY_CHECKED);

            cart.setStatus(1);

            _cartRepository.save(cart);

            return new SuccessResult(Messages.CART_CHECKOUT_SUCCESS);
        }
        return new ErrorResult(Messages.CART_CHECKOUT_FAILURE);
    }

    /**
     Find cart with given id. If exist map to view dto.
     @return SuccessDataResult,ErrorResult with appropriate message.
     @param cartId cart to find.
     */
    public Result getCartById(long cartId){
        Optional<Cart> cartOptional = _cartRepository.findById(cartId);

        if (cartOptional.isPresent()){
            return new SuccessDataResult<>(Mapper.cartEntityToDto(cartOptional.get()),Messages.CART_FOUND);
        }
        return new ErrorResult(Messages.CART_NOT_FOUND);
    }

}

package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.services.abstarcts.CartProductService;
import com.fiba.shoppingservice.data.repositories.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductManager implements CartProductService {
    @Autowired
    private CartProductRepository _cartProductRepository;


    /**
     Delete product in the cart with given id.
     @return SuccessDataResult,ErrorResult with appropriate message.
     @param cartProductId
     */
    public void removeProductById(long cartProductId){
        _cartProductRepository.deleteById(cartProductId);
    }

}

package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.services.abstarcts.CartProductService;
import com.fiba.shoppingservice.data.repositories.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductManager implements CartProductService {
    @Autowired
    private CartProductRepository _cartProductRepository;

    public void removeProductById(long id){
        _cartProductRepository.deleteById(id);
    }

}

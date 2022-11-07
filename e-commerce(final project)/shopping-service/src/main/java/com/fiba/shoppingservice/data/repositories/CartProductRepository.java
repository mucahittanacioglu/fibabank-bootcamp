package com.fiba.shoppingservice.data.repositories;

import com.fiba.shoppingservice.data.entities.CartProduct;
import org.springframework.data.repository.CrudRepository;

public interface CartProductRepository extends CrudRepository<CartProduct,Long> {
}

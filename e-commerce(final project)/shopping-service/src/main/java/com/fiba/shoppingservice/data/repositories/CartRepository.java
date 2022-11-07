package com.fiba.shoppingservice.data.repositories;

import com.fiba.shoppingservice.data.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
}

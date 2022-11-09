package com.fiba.shoppingservice.data.repositories;

import com.fiba.shoppingservice.data.entities.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart,Long> {
    @Query("select c from Cart as c where c.customerName= :customerName")
    Optional<Cart> findCartByName(@Param("customerName") String customerName);
}

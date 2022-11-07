package com.example.orm.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

        @Query("select p from Product as p where p.price >= :minPrice")
        List<Product> findAllByPriceMin(@Param("minPrice") double minPrice);
}

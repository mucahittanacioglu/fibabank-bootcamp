package com.fiba.inventoryservice.data.repositories;

import com.fiba.inventoryservice.data.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("select p from Product as p where p.category.categoryId >= :categoryId")
    List<Product> findProductsByCategory(@Param("categoryId") long categoryId);
}

package com.fiba.inventoryservice.data.repositories;

import com.fiba.inventoryservice.data.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}

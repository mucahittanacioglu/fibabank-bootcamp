package com.fiba.inventoryservice.business.services.abstracts;

import com.fiba.inventoryservice.business.dto.CategoryViewWithProductDto;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.utilities.results.DataResult;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    DataResult<?> getCategoryWithData(long categoryId);

    boolean existById(long categoryId);
}

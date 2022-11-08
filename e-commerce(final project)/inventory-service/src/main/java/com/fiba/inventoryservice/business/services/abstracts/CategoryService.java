package com.fiba.inventoryservice.business.services.abstracts;

import com.fiba.inventoryservice.business.dto.CategoryViewWithProductDto;
import com.fiba.inventoryservice.utilities.results.DataResult;

import java.util.List;

public interface CategoryService {
    DataResult<?> getCategoryWithData(long categoryId);
}

package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.dto.CategoryDto;
import com.fiba.inventoryservice.business.services.abstracts.CategoryService;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.repositories.CategoryRepository;
import com.fiba.inventoryservice.utilities.results.DataResult;
import com.fiba.inventoryservice.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    @Autowired
    private CategoryRepository _categoryRepository;

    public DataResult<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoriesDto = new ArrayList<>();
        _categoryRepository.findAll()
                .forEach(category ->
                        categoriesDto
                                .add(toDto(category)));

        return new SuccessDataResult<>(categoriesDto);
    }
    public void addCategory(Category ct){
        _categoryRepository.save(ct);
    }

    /* Mapper functions */
    private CategoryDto toDto(Category category){
        return new CategoryDto(category.getCategoryName());
    }
    private Category toEntity(CategoryDto categoryDto){
        return new Category(categoryDto.getCategoryName());
    }
}

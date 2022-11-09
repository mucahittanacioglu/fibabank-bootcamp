package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.Mapper;
import com.fiba.inventoryservice.business.dto.CategoryInsertionDto;
import com.fiba.inventoryservice.business.dto.CategoryViewDto;
import com.fiba.inventoryservice.business.dto.CategoryViewWithProductDto;
import com.fiba.inventoryservice.business.services.abstracts.CategoryService;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.repositories.CategoryRepository;
import com.fiba.inventoryservice.utilities.Messages.Messages;
import com.fiba.inventoryservice.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryManager implements CategoryService {

    @Autowired
    private CategoryRepository _categoryRepository;

    public DataResult<List<CategoryViewDto>> getAllCategories(){
        List<CategoryViewDto> categoriesDto = new ArrayList<>();
        _categoryRepository.findAll()
                .forEach(category ->
                        categoriesDto
                                .add(Mapper.categoryEntityToViewDto(category)));

        return new SuccessDataResult<>(categoriesDto);
    }
    public void addCategoryAsEntity(Category category){
        _categoryRepository.save(category);
    }

    public Result addCategory(CategoryInsertionDto categoryInsertionDto){
        _categoryRepository.save(Mapper.categoryInsertionDtoToEntity(categoryInsertionDto));
        return new SuccessResult(Messages.CATEGORY_ADD_SUCCESS);
    }
    public Result removeCategory(long categoryId){
        _categoryRepository.deleteById(categoryId);
        return new SuccessResult(Messages.CATEGORY_DELETE_SUCCESS);
    }

    public Optional<Category> findCategoryById(long categoryID){
        return _categoryRepository.findById(categoryID);
    }


    @Override
    public DataResult<?> getCategoryWithData(long categoryId) {
        Optional<Category> categoryOptional = findCategoryById(categoryId);
        if (categoryOptional.isPresent()){
            return  new SuccessDataResult<>(Mapper.categoryEntityToViewWithProductDto(categoryOptional.get()),Messages.CATEGORY_FOUND);
        }
        return new ErrorDataResult(null,Messages.CATEGORY_NOT_FOUND);
    }
    @Override
    public boolean existById(long categoryId) {
        return _categoryRepository.existsById(categoryId);
    }
}

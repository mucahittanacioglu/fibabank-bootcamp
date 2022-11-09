package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.Mapper;
import com.fiba.inventoryservice.business.dto.CategoryInsertionDto;
import com.fiba.inventoryservice.business.dto.CategoryViewDto;
import com.fiba.inventoryservice.business.dto.CategoryViewWithProductDto;
import com.fiba.inventoryservice.business.services.abstracts.CategoryService;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;
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

    public CategoryManager(CategoryRepository _categoryRepository) {
        this._categoryRepository = _categoryRepository;
    }

    /**
     Get all categories with their products.
     @return SuccessDataResult with appropriate message.
     */
    public DataResult<List<CategoryViewDto>> getAllCategories(){
        //Find all categories and amp each one to view dto.
        List<CategoryViewDto> categoriesDto = new ArrayList<>();
        _categoryRepository.findAll()
                .forEach(category ->
                        categoriesDto
                                .add(Mapper.categoryEntityToViewDto(category)));

        return new SuccessDataResult<>(categoriesDto);
    }

    /**
     Add new category to database.
     @return SuccessDataResult with appropriate message.
     @param categoryInsertionDto category to add.
     */
    public Result addCategory(CategoryInsertionDto categoryInsertionDto){
        //map incoming dto to category entity
        Category categoryToSave = Mapper.categoryInsertionDtoToEntity(categoryInsertionDto);
        Category category =  _categoryRepository.save(categoryToSave);
        return new SuccessDataResult<Long>(category.getCategoryId(),Messages.CATEGORY_ADD_SUCCESS);
    }

    /**
     Delete category from database with given id.
     @return SuccessResult with appropriate message.
     @param categoryId category to delete.
     */
    public Result removeCategory(long categoryId){
        _categoryRepository.deleteById(categoryId);
        return new SuccessResult(Messages.CATEGORY_DELETE_SUCCESS);
    }

    /**
     Find category from database with given id.
     @return Optional<Category>
     @param categoryId category to find.
     */
    public Optional<Category> findCategoryById(long categoryId){
        return _categoryRepository.findById(categoryId);
    }

    /**
     Find category from database with products belongs to it.
     @return SuccessDataResult,ErrorDataResult with appropriate message.
     @param categoryId category to find.
     */
    @Override
    public DataResult<?> getCategoryWithData(long categoryId) {
        Optional<Category> categoryOptional = findCategoryById(categoryId);
        //if category exist map category entity to view dto.
        if (categoryOptional.isPresent()){
            return  new SuccessDataResult<>(Mapper.categoryEntityToViewWithProductDto(categoryOptional.get()),Messages.CATEGORY_FOUND);
        }
        return new ErrorDataResult(null,Messages.CATEGORY_NOT_FOUND);
    }
    /**
     Check whether category with given id exist in database.
     @return boolean
     @param categoryId category to check.
     */
    @Override
    public boolean existById(long categoryId) {
        return _categoryRepository.existsById(categoryId);
    }
}

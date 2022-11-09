package com.fiba.inventoryservice.presentation.rest;

import com.fiba.inventoryservice.business.dto.CategoryInsertionDto;
import com.fiba.inventoryservice.business.dto.CategoryViewDto;
import com.fiba.inventoryservice.business.dto.CategoryViewWithProductDto;
import com.fiba.inventoryservice.business.services.concretes.CategoryManager;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;
import com.fiba.inventoryservice.utilities.results.DataResult;
import com.fiba.inventoryservice.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class CategoryController {
    @Autowired
    private CategoryManager _categoryManager;

    @GetMapping("/categories")
    public DataResult<List<CategoryViewDto>> getAllCategories(){
        return _categoryManager.getAllCategories();
    }
    @GetMapping("/category/{categoryId}")
    public DataResult<?> getCategoryWithData(@PathVariable("categoryId")long categoryId){
        return _categoryManager.getCategoryWithData(categoryId);
    }

    @PostMapping("/category/add")
    public Result createCategory(@RequestBody CategoryInsertionDto categoryInsertionDto){
        return _categoryManager.addCategory(categoryInsertionDto);
    }
    @DeleteMapping("/category/delete/{categoryId}")
    public Result deleteCategoryById(@PathVariable("categoryId") long categoryId){
        return _categoryManager.removeCategory(categoryId);
    }

}

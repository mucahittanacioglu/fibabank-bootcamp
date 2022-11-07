package com.fiba.inventoryservice.presentation.rest;

import com.fiba.inventoryservice.business.dto.CategoryDto;
import com.fiba.inventoryservice.business.services.concretes.CategoryManager;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;
import com.fiba.inventoryservice.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class CategoryController {
    @Autowired
    private CategoryManager _categoryManager;

    @GetMapping("/categories")
    public DataResult<List<CategoryDto>> getAllCategories(){
        return _categoryManager.getAllCategories();
    }

    @GetMapping("/categories/insert")
    public String insertCategories(){
        Category ct = new Category("Shirts");
        ct.setProductList(new ArrayList<>());

        Product p1 = new Product("White Shirt",34);
        p1.setCategory(ct);
        ct.getProductList().add(p1);

        Product p2 = new Product("Brown Shirt",25);
        p2.setCategory(ct);
        ct.getProductList().add(p2);

        _categoryManager.addCategory(ct);
        return "Success!";
    }
}

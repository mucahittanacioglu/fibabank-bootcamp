package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.dto.CategoryInsertionDto;
import com.fiba.inventoryservice.business.dto.CategoryViewWithProductDto;
import com.fiba.inventoryservice.business.dto.ProductInsertionDto;
import com.fiba.inventoryservice.data.entities.Product;
import com.fiba.inventoryservice.data.repositories.CategoryRepository;
import com.fiba.inventoryservice.data.repositories.ProductRepository;
import com.fiba.inventoryservice.utilities.Messages.Messages;
import com.fiba.inventoryservice.utilities.results.DataResult;
import com.fiba.inventoryservice.utilities.results.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ProductManagerTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductManager _productManager;
    @Autowired
    private CategoryManager _categoryManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenProductAddCalledWithNonExistCategory_itShouldReturnErrorResult() {
        ProductInsertionDto notValidProduct = getNonValidProduct();
        Result result = _productManager.addProduct(notValidProduct);
        assertEquals(result.isSuccess(),false);
        assertEquals(result.getMessage(),Messages.CATEGORY_NOT_FOUND);
    }

    @Test
    void whenDeleteProductCalledWithNonExistingProductId_itShouldReturnErrorResult() {
        Result result = _productManager.deleteProductWithId(-1);
        assertEquals(result.isSuccess(),false);
        assertEquals(result.getMessage(),Messages.PRODUCT_NOT_FOUND);
    }

    @Test
    void whenGetProductWithIdCalledWithNonExistingProductId_itShouldReturnErrorResult() {
        Result result = _productManager.getProductWithId(-1);
        assertEquals(result.isSuccess(),false);
        assertEquals(result.getMessage(),Messages.PRODUCT_NOT_FOUND);
    }

    @Test
    void whenAddProductCalledWithValidProduct_itShouldReturnSuccessResultWithProductId(){
        DataResult<Long> category = (DataResult<Long>)_categoryManager.addCategory(getCategory());
        assertEquals(category.isSuccess(),true);

        ProductInsertionDto productInsertionDto = getValidProduct(category.getData());

        Result result = _productManager.addProduct(productInsertionDto);

        assertEquals(result.isSuccess(),true);
        assertEquals(result.getMessage(),Messages.PRODUCT_ADD_SUCCESS);
        assertInstanceOf(Long.class,((DataResult<Long>)result).getData());
    }
    void whenAddProductCalledWithValidProductId_itShouldAlsoAddToCategoryList(){
        DataResult<Long> category = (DataResult<Long>)_categoryManager.addCategory(getCategory());

        ProductInsertionDto productInsertionDto = getValidProduct(category.getData());

        Result productResult = _productManager.addProduct(productInsertionDto);

        Result result = _categoryManager.getCategoryWithData(category.getData());
        CategoryViewWithProductDto categoryViewWithProductDto = ((DataResult<CategoryViewWithProductDto>)result).getData();

        assertEquals(categoryViewWithProductDto.getProducts().size(),1);

        assertEquals(categoryViewWithProductDto.getProducts().get(0).getProductId(),(( DataResult<Long>)productResult).getData());
    }
    void whenDeleteProductCalledWithValidProductId_itShouldAlsoDeleteFromCategoryList(){
        DataResult<Long> category = (DataResult<Long>)_categoryManager.addCategory(getCategory());

        ProductInsertionDto productInsertionDto = getValidProduct(category.getData());

        Result result = _productManager.addProduct(productInsertionDto);

        _productManager.deleteProductWithId(((DataResult<Long>)result).getData());

        Result resultCategory = _categoryManager.getCategoryWithData(category.getData());
        CategoryViewWithProductDto categoryViewWithProductDto = ((DataResult<CategoryViewWithProductDto>)resultCategory).getData();

        assertEquals(categoryViewWithProductDto.getProducts().size(),0);
    }

    private CategoryInsertionDto getCategory(){
        return new CategoryInsertionDto("Test category");
    }
    private ProductInsertionDto getValidProduct(long categoryID){
        return new ProductInsertionDto(categoryID,"Test",25);
    }
    private ProductInsertionDto getNonValidProduct(){
        return new ProductInsertionDto(-1,"Test",25);
    }
}
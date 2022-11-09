package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.dto.CategoryInsertionDto;
import com.fiba.inventoryservice.data.repositories.CategoryRepository;
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
class CategoryManagerTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryManager _categoryManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenCategoryCreateCalledWithValidArguments_itShouldReturnSuccessResultWithCategoryId(){
        Result result = _categoryManager.addCategory(getCategory());
        assertEquals(result.isSuccess(),true);
        assertInstanceOf(DataResult.class,result);
        assertInstanceOf(Long.class,((DataResult<Long>)result).getData());
    }

    private CategoryInsertionDto getCategory(){
        return new CategoryInsertionDto("Test category");
    }
}
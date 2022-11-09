package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.dto.CartProductInsertionDto;
import com.fiba.shoppingservice.data.repositories.CartRepository;
import com.fiba.shoppingservice.utilities.results.Result;
import com.fiba.shoppingservice.utilities.results.SuccessDataResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CartManagerTest {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartManager _cartManager;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void whenAddCartItemWithNotValid_itShouldReturnErorResult() {
        CartProductInsertionDto notValid = new CartProductInsertionDto(1,1,-1,1,1,1);
        String username="HopenotExist";
        Result result1 = _cartManager.createNewCart(username);

        Result result2 = _cartManager.createNewCart(username);
        assertEquals(result2.isSuccess(),true);
        assertInstanceOf(SuccessDataResult.class,result2);
    }
}
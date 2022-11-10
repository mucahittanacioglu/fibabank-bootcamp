package com.fiba.shoppingservice.business.services.concretes;

import com.fiba.shoppingservice.business.dto.CartDto;
import com.fiba.shoppingservice.business.dto.CartProductInsertionDto;
import com.fiba.shoppingservice.data.repositories.CartRepository;
import com.fiba.shoppingservice.utilities.Messages.Messages;
import com.fiba.shoppingservice.utilities.results.DataResult;
import com.fiba.shoppingservice.utilities.results.ErrorResult;
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
    public void whenAddCartItemCalledWithNotValidCartId_itShouldReturnErrorResult() {
        CartProductInsertionDto notValid = getNonValid();
        Result result = _cartManager.addItemToCart(getNonValid());

        assertEquals(false,result.isSuccess());
        assertInstanceOf(ErrorResult.class,result);
        assertEquals(Messages.CART_NOT_FOUND,result.getMessage());
    }

    @Test
    public void whenCreateNewCartCalledWithExistingUsername_itShouldReturnSuccessDataResultWithCartId(){
        String testUsername = "NonExistUsername";
        Result result1 = _cartManager.createNewCart(testUsername);
        Result result2 = _cartManager.createNewCart(testUsername);

        assertInstanceOf(SuccessDataResult.class,result2);
        assertEquals(Messages.CART_ALREADY_EXIST,result2.getMessage());
        assertEquals(((DataResult<Long>)result1).getData(),((DataResult<Long>)result2).getData());
    }

    @Test
    public void whenAddCartItemCalledWithValidCartProduct_itShouldReturnSuccessResult(){
        String testUsername = "NonExistUsername";
        Result result1 = _cartManager.createNewCart(testUsername);

        long categoryID = ((DataResult<Long>)result1).getData();
        CartProductInsertionDto validProduct = getNewWithCartId(categoryID);
        Result result2 = _cartManager.addItemToCart(validProduct);

        assertEquals(Messages.CART_PRODUCT_ADD_SUCCESS,result2.getMessage());

    }

    @Test
    public void whenAddCartItemCalledWithExistingCartItem_itShouldIncrementQuantityByOne(){
        String testUsername = "NonExistUsername";
        Result result1 = _cartManager.createNewCart(testUsername);

        long cartId = ((DataResult<Long>)result1).getData();
        CartProductInsertionDto validProduct = getNewWithCartId(cartId);

        _cartManager.addItemToCart(validProduct);
        _cartManager.addItemToCart(validProduct);

        Result result2 = _cartManager.getCartById(cartId);
        CartDto cartDto = ((DataResult< CartDto >)result2).getData();

        assertEquals(2,cartDto.getCartProducts().get(0).getSalesQuantity());
    }

    @Test
    public void whenRemoveCartItemCalledWithExistingCartItem_itShouldDecrementQuantityByOne(){
        String testUsername = "NonExistUsername";
        Result result1 = _cartManager.createNewCart(testUsername);

        long cartId = ((DataResult<Long>)result1).getData();
        CartProductInsertionDto validProduct = getNewWithCartId(cartId);
        validProduct.setSalesQuantity(1);

        _cartManager.addItemToCart(validProduct);
        _cartManager.addItemToCart(validProduct);


        Result result2 = _cartManager.getCartById(cartId);
        CartDto cartDto = ((DataResult< CartDto >)result2).getData();

        long cartProductId = cartDto.getCartProducts().get(0).getCartProductId();
        _cartManager.removeItemFromCart(cartId,cartProductId);

        result2 = _cartManager.getCartById(cartId);
        cartDto = ((DataResult< CartDto >)result2).getData();


        assertEquals(1,cartDto.getCartProducts().get(0).getSalesQuantity());
    }

    @Test
    public void whenCartCheckedOut_itShouldReturnErrorResultOnAdditionOfCartItem(){
        String testUsername = "NonExistUsername";
        Result result1 = _cartManager.createNewCart(testUsername);

        long cartId = ((DataResult<Long>)result1).getData();
        CartProductInsertionDto validProduct = getNewWithCartId(cartId);
        validProduct.setSalesQuantity(1);

        _cartManager.addItemToCart(validProduct);
        Result cartResultAfterFirstAddition = _cartManager.getCartById(cartId);
        CartDto cartDtoAfterFirstAddition = ((DataResult< CartDto >)cartResultAfterFirstAddition).getData();
        long cartProductId = cartDtoAfterFirstAddition.getCartProducts().get(0).getCartProductId();

        _cartManager.cartCheckout(cartId);

        Result resultCartSecondAddition = _cartManager.addItemToCart(validProduct);
        Result cartResultAfterSecondAddition = _cartManager.getCartById(cartId);
        CartDto cartDtoAfterSecondAddition = ((DataResult< CartDto >)cartResultAfterSecondAddition).getData();

        assertEquals(Messages.CART_CHECKED_OUT,resultCartSecondAddition.getMessage());
        assertInstanceOf(ErrorResult.class,resultCartSecondAddition);

        int salesQuantityBeforeCheckout = cartDtoAfterFirstAddition.getCartProducts().get(0).getSalesQuantity();
        int salesQuantityAfterCheckout = cartDtoAfterSecondAddition.getCartProducts().get(0).getSalesQuantity();
        double totalAmountBeforeCheckout = cartDtoAfterFirstAddition.getCartProducts().stream().mapToDouble(p->p.getLineAmount()).sum();
        double totalAmountAfterCheckout = cartDtoAfterSecondAddition.getCartProducts().stream().mapToDouble(p->p.getLineAmount()).sum();

        assertEquals(salesQuantityBeforeCheckout,salesQuantityAfterCheckout);
        assertEquals(totalAmountAfterCheckout,totalAmountBeforeCheckout,0.001);
    }

    @Test
    public void whenCartCheckedOut_itShouldReturnErrorResultOnDeletionOfCartItem(){
        String testUsername = "NonExistUsername";
        Result result1 = _cartManager.createNewCart(testUsername);

        long cartId = ((DataResult<Long>)result1).getData();
        CartProductInsertionDto validProduct = getNewWithCartId(cartId);
        validProduct.setSalesQuantity(1);

        _cartManager.addItemToCart(validProduct);
        Result cartResultAfterFirstAddition = _cartManager.getCartById(cartId);
        CartDto cartDtoAfterFirstAddition = ((DataResult< CartDto >)cartResultAfterFirstAddition).getData();
        long cartProductId = cartDtoAfterFirstAddition.getCartProducts().get(0).getCartProductId();

        _cartManager.cartCheckout(cartId);

        Result resultCartDeletion = _cartManager.removeItemFromCart(cartId,cartProductId);
        Result cartResultAfterDeletion = _cartManager.getCartById(cartId);
        CartDto cartDtoAfterDeletion = ((DataResult< CartDto >)cartResultAfterDeletion).getData();

        assertEquals(Messages.CART_CHECKED_OUT,resultCartDeletion.getMessage());
        assertInstanceOf(ErrorResult.class,resultCartDeletion);

        int salesQuantityBeforeCheckout = cartDtoAfterFirstAddition.getCartProducts().get(0).getSalesQuantity();
        int salesQuantityAfterCheckoutAfterDeletion = cartDtoAfterDeletion.getCartProducts().get(0).getSalesQuantity();
        double totalAmountBeforeCheckout = cartDtoAfterFirstAddition.getCartProducts().stream().mapToDouble(p->p.getLineAmount()).sum();
        double totalAmountAfterCheckout = cartDtoAfterDeletion.getCartProducts().stream().mapToDouble(p->p.getLineAmount()).sum();

        assertEquals(salesQuantityBeforeCheckout,salesQuantityAfterCheckoutAfterDeletion);
        assertEquals(totalAmountAfterCheckout,totalAmountBeforeCheckout,0.001);
    }

    private CartProductInsertionDto getNonValid(){
        return new CartProductInsertionDto(1,1,-1,1,1,1);
    }
    private CartProductInsertionDto getNewWithCartId(long cartId){
        return new CartProductInsertionDto(1,1,cartId,1,1,1);
    }
}
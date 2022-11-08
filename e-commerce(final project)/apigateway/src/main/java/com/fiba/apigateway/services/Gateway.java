package com.fiba.apigateway.services;

import com.fiba.apigateway.dtos.*;
import com.fiba.apigateway.services.components.InventoryComponent;
import com.fiba.apigateway.services.components.ShoppingComponent;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Gateway {
    @Autowired
    InventoryComponent _inventoryComponent;
    @Autowired
    ShoppingComponent _shoppingComponent;

    public DataResult<List<CategoryDto>> getAlCategories(){
        return _inventoryComponent.getAllCategories();
    }
    public DataResult<ProductDto> getProductById(long productId){
        return _inventoryComponent.getProductWithId(productId);
    }
    public String getProductByCategory(long categoryId){
        return _inventoryComponent.getProductsByCategoryId(categoryId);
    }
    public String createCart(){
        return _shoppingComponent.createCart();
    }
    public String createCart(String customerName){
        return _shoppingComponent.createCart(customerName);
    }


    public Result addProductToCart(CartProductInsertDto cartProductInsertDto){
        return _shoppingComponent.addProductToCart(cartProductInsertDto);
    }
    public Result deleteItemFromCart(long cartId,long productId){
        return _shoppingComponent.deleteItemFromCart(cartId, productId);
    }
    public Result checkout(long cartId){
        return _shoppingComponent.checkout(cartId);
    }

    public Result findCartById(long cartId){
        Result result = _shoppingComponent.findCartById(cartId);
        //get product name from inventory service
        if (result.isSuccess()){
            CartViewDto cartViewDto = toCartViewDto(((DataResult<CartInsertDto>)result).getData());
            return new DataResult<>(cartViewDto,result.isSuccess(),result.getMessage());
        }
        return result;
    }

    public Result addCategory(CategoryInsertionDto categoryInsertionDto) {
        return _inventoryComponent.addCategory(categoryInsertionDto);
    }
    public Result removeCategory(long categoryId) {
        return _inventoryComponent.removeCategory(categoryId);
    }

    public Result addProduct(ProductInsertionDto productInsertionDto) {
        return _inventoryComponent.addProduct(productInsertionDto);
    }

    public Result deleteProductWithId(long productId) {
        return _inventoryComponent.deleteProductWithId(productId);
    }

    public String getCategoryWithData(long categoryId) {
        return _inventoryComponent.getCategoryWithData(categoryId);
    }

    private CartProductViewDto toCartProductViewDto(CartProductInsertDto cartProductInsertDto,long cartId){
        String productName = _inventoryComponent.getProductWithId(cartProductInsertDto.getCartProductId()).getData().getProductName();
        return new CartProductViewDto(cartProductInsertDto.getCartProductId(), cartId, cartProductInsertDto.getProductId(),
                cartProductInsertDto.getSalesQuantity(),cartProductInsertDto.getSalesPrice(),cartProductInsertDto.getLineAmount(),productName);
    }
    private CartViewDto toCartViewDto(CartInsertDto cartInsertDto){
        CartViewDto cartViewDto = new CartViewDto(cartInsertDto.getCartId(), cartInsertDto.getCustomerName(),new ArrayList<CartProductViewDto>());
        cartInsertDto.getCartProducts().forEach(cartProductInsertDto -> cartViewDto.getCartProducts().add(toCartProductViewDto(cartProductInsertDto,cartViewDto.getCartId())));
        return cartViewDto;
    }
}

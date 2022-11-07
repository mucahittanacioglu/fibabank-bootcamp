package com.fiba.apigateway.services.components;

import com.fiba.apigateway.dtos.CartDto;
import com.fiba.apigateway.dtos.CartProductDto;
import com.fiba.apigateway.dtos.ProductDto;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ShoppingComponent {
    private final String shoppingServiceUrl = "http://localhost:8082/shopping";
    private String cartCreateRoute = "/cart/create";
    private String cartItemAddRoute = "/cart/add";
    private String cartCheckoutRoute = "/cart/checkout/";
    private String cartFindByIdRoute = "/cart/find/";


    public Result createCart(){
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.getForObject(shoppingServiceUrl+cartCreateRoute,Result.class);
        return result;
    }

    public Result addProductToCart(CartProductDto cartProductDto){
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(shoppingServiceUrl+cartItemAddRoute, HttpMethod.POST,new HttpEntity<>(cartProductDto),
               Result.class).getBody();
        return result;
    }
    public Result deleteItemFromCart(long cartId,long productId){
        String deleteRoute = "/cart/"+cartId+"/remove/"+productId;
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(shoppingServiceUrl+deleteRoute,HttpMethod.DELETE,
                null,Result.class).getBody();
        return result;
    }
    public Result checkout(long cartId){
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(shoppingServiceUrl+cartCheckoutRoute+cartId,HttpMethod.GET,
                null,Result.class).getBody();
        return result;
    }
    public Result find(long cartId){
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(shoppingServiceUrl+cartFindByIdRoute+cartId,HttpMethod.GET,
                null,new ParameterizedTypeReference<DataResult<CartDto>>(){}).getBody();
        return result;
    }

}

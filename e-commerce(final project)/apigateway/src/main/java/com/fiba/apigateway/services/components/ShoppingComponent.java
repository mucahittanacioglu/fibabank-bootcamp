package com.fiba.apigateway.services.components;

import com.fiba.apigateway.dtos.CartInsertDto;
import com.fiba.apigateway.dtos.CartProductInsertDto;
import com.fiba.apigateway.dtos.CartProductViewDto;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ShoppingComponent {
    private final String shoppingServiceUrl = "http://localhost:8082/shopping";
    private String cartCreateRoute = "/cart/create";
    private String cartItemAddRoute = "/cart/add";
    private String cartCheckoutRoute = "/cart/checkout/";
    private String cartFindByIdRoute = "/cart/find/";


    public String createCart(){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(shoppingServiceUrl+cartCreateRoute,String.class);
    }
    public String createCart(String customerName){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(shoppingServiceUrl+cartCreateRoute+"/"+customerName,String.class);
    }

    public Result addProductToCart(CartProductInsertDto cartProductInsertDto){
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(shoppingServiceUrl+cartItemAddRoute, HttpMethod.POST,new HttpEntity<>(cartProductInsertDto),
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
    public Result findCartById(long cartId){
        RestTemplate restTemplate = new RestTemplate();
        DataResult<CartInsertDto> result = restTemplate.exchange(shoppingServiceUrl+cartFindByIdRoute+cartId,HttpMethod.GET,
                null,new ParameterizedTypeReference<DataResult<CartInsertDto>>(){}).getBody();
        /*return restTemplate.exchange(shoppingServiceUrl+cartFindByIdRoute+cartId,HttpMethod.GET,
                null,String.class).getBody();*/
        return result;
    }

}

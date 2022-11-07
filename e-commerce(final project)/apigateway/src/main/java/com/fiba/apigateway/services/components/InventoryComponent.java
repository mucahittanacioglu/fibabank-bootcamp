package com.fiba.apigateway.services.components;

import com.fiba.apigateway.dtos.CategoryDto;
import com.fiba.apigateway.dtos.ProductDto;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@Component
public class InventoryComponent {
    private final String inventoryServiceUrl = "http://localhost:8081/inventory";

   public DataResult<List<CategoryDto>> getAllCategories(){
       String temp = "/categories";
       RestTemplate restTemplate = new RestTemplate();
       DataResult<List<CategoryDto>> result = restTemplate.exchange(inventoryServiceUrl+temp, HttpMethod.GET,null
               ,new ParameterizedTypeReference<DataResult<List<CategoryDto>>>(){}).getBody();
       return result;
   }
    public DataResult<ProductDto> getProductWithId(long productId){
        String temp = "/product/"+productId;
        RestTemplate restTemplate = new RestTemplate();
        DataResult<ProductDto> result = restTemplate.exchange(inventoryServiceUrl+temp, HttpMethod.GET,null
                ,new ParameterizedTypeReference<DataResult<ProductDto>>(){}).getBody();
        return result;
    }
    public DataResult<List<ProductDto>> getProductsByCategoryId(long categoryId){
        String temp = "/products/"+categoryId;
        RestTemplate restTemplate = new RestTemplate();
        DataResult<List<ProductDto>> result = restTemplate.exchange(inventoryServiceUrl+temp, HttpMethod.GET,null,
                new ParameterizedTypeReference<DataResult<List<ProductDto>>>(){}).getBody();
        return result;
    }


}

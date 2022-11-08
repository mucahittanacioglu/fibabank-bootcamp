package com.fiba.apigateway.services.components;

import com.fiba.apigateway.dtos.CategoryDto;
import com.fiba.apigateway.dtos.CategoryInsertionDto;
import com.fiba.apigateway.dtos.ProductDto;
import com.fiba.apigateway.dtos.ProductInsertionDto;
import com.fiba.apigateway.utilities.results.DataResult;
import com.fiba.apigateway.utilities.results.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@Component
public class InventoryComponent {
    private final String inventoryServiceUrl = "http://localhost:8081/inventory";
    private final String getCategoriesRoute = "/categories";
    private final String addProductRoute = "/product/add";
    private final String deleteProductRoute = "/product/delete/";
    private final String getProductRoute = "/product/";
    private final String getProductsByCategoryRoute = "/products/";
    private final String addCategoryRoute = "/category/add";
    private final String deleteCategoryRoute = "/category/delete/";

   public DataResult<List<CategoryDto>> getAllCategories(){
       RestTemplate restTemplate = new RestTemplate();
       DataResult<List<CategoryDto>> result = restTemplate.exchange(inventoryServiceUrl+getCategoriesRoute, HttpMethod.GET,null
               ,new ParameterizedTypeReference<DataResult<List<CategoryDto>>>(){}).getBody();
       return result;
   }
    public DataResult<ProductDto> getProductWithId(long productId){
        RestTemplate restTemplate = new RestTemplate();
        DataResult<ProductDto> result = restTemplate.exchange(inventoryServiceUrl+getProductRoute+productId,
                HttpMethod.GET,null
                ,new ParameterizedTypeReference<DataResult<ProductDto>>(){}).getBody();
        return result;
    }
    public String getProductsByCategoryId(long categoryId){
        RestTemplate restTemplate = new RestTemplate();
        /*DataResult<List<ProductDto>> result = restTemplate.exchange(inventoryServiceUrl+temp, HttpMethod.GET,null,
                new ParameterizedTypeReference<DataResult<List<ProductDto>>>(){}).getBody();*/

        return  restTemplate.exchange(inventoryServiceUrl+getProductsByCategoryRoute+categoryId,
                HttpMethod.GET,null,String.class).getBody();
    }
    public Result addCategory(CategoryInsertionDto categoryInsertionDto) {
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(inventoryServiceUrl+ addCategoryRoute, HttpMethod.POST
                ,new HttpEntity<>(categoryInsertionDto),Result.class).getBody();
        return result;
    }

    public Result removeCategory(long categoryId) {
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(inventoryServiceUrl+ deleteCategoryRoute +categoryId, HttpMethod.DELETE
                ,null,Result.class).getBody();
        return result;
    }

    public Result addProduct(ProductInsertionDto productInsertionDto) {
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(inventoryServiceUrl+ addProductRoute, HttpMethod.POST
                ,new HttpEntity<>(productInsertionDto),Result.class).getBody();
        return result;
    }

    public Result deleteProductWithId(long productId) {
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.exchange(inventoryServiceUrl+ deleteProductRoute +productId, HttpMethod.DELETE
                ,null,Result.class).getBody();
        return result;
    }
}

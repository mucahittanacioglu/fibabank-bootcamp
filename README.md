# Fibabank-Bootcamp

In this project, I implemented a basic e-commerce project with microservice architecture on java-spring.

## 1-)Project Overview

There are 3 microservices including API-Gateway. These are Inventory Service, Shopping Service and Gate Way. Each service has tradetional 3-layered architecture. I also created a client with Angular. 

### 1-a) Inventory Service

The Inventory service has 2 entities Category and Product. The category has OneToMany relation with Product and Product has ManyToOne relation. I applied SOLID principles with 3 layered designs. There are business, data, presentation layers and utility packages. The data layer has entity definitions and corresponding repositories. The business layer has a manager for entities and depends on entity repositories, also includes dto classes for entities. The presentation layer has rest controllers and depends on managers. The utility package has a static Messages class for all response texts and has Result classes.  Result classes were used for creating responses and used other services as well.

The Inventory service has **_"/inventory"_** prefix for the following routes.

| Method Type  | Route                         | Parameters             |Description |
| -------------| -------------                 | -------------      | ------------- |
| _GET_        | **/product/{productId}** |Only path variable. |Returns product with given _prdouctId_ if exist.|
| _GET_        | **/products/{categoryId}** |Only path varible. |Returns prodcuts under category for given _categoryId_ if exist.|
| _POST_       | **/product/add** |Prodcut object in request body.|Returns product addition status. Category must exist beforehand.|
| _DELETE_     | **/product/delete/{productId}** |Only path variable|Returns deletion status.|
| _GET_        | **/categories** |No parameter.|Returns all categeries.|
| _GET_        | **/category/{categoryId}** |Only path variable.|Returns category for given _categoryId_.|
| _POST_        | **/category/add** |Category object in request body.|Returns category addition status.|
| _DELETE_        | **/category/delete/{categoryId}** |Only path variable.|Returns deletion status.|

### 1-b) Shopping Service

The Shopping service has 2 entities Cart and Cart Product. The Cart has OneToMany relation with Product relation.
Other details are the same with Inventory Service.

The Shopping service has **_"/shopping"_** prefix for the following routes.
| Method Type  | Route                         | Parameters             |Description
| -------------| -------------                 | -------------      | ------------- |
| _GET_        | **/cart/create/{customerName}** |Only path variable. |Returns created or existing cart's id.|
| _GET_        | **/cart/checkout/{cartId}** |Only path variable. |Returns status of cart checkout.|
| _GET_        | **/cart/find/{cartId}** |Only path variable. |Returns cart with given _cartId_ if exist.|
| _POST_        | **/cart/add** |Cart product object in request body.|Returns status of product addition to cart.|
| _DELETE_        | **/cart/{cartId}/remove/{productId}** |Only path variables.|Returns status of product deletion of cart.|

## 1-c) API-Gateway

The Gateway has all the aforementioned routes with the prefix **_"/commerce"_** with the same properties. For all the routes gateway just exchanges request except **/cart/find/{cartId}**. For getting a cart, cart products do not have _productName_ field to send this to UI, therefore gateway uses the Inventory service to find product names.

## 1-d) Curl commands

Some curl commands

|Curl command |Description
| -------------| ------------- 
|curl http://localhost:8080/commerce/inventory/categories| Get all categories.
|curl http://localhost:8080/commerce/inventory/product/18| Get prodcut with id 18.
|curl -d "{\"categoryId\":1,\"productName\":\"Test cup\",\"salesPrice\":3}" -H "Content-Type: application/json" http://localhost:8080/commerce/inventory/product/add| Add product |
|curl http://localhost:8080/commerce/shopping/cart/find/4| Find cart with id 4.

Some example output

|Success|Fail
| -------------| ------------- 
|![](readme-resource/images/curl1-suc.png) |![](readme-resource/images/curl1-fail.png)
|![](readme-resource/images/curl2-suc.png) |![](readme-resource/images/curl2-fail.png)
|![](readme-resource/images/curl3-suc.png) |![](readme-resource/images/curl3-fail.png)
|![](readme-resource/images/curl4-suc.png) |![](readme-resource/images/curl4-fail.png)

## 1-e) Angular UI
The client was created with Angular and it connects to Gateway service only. Some of the entity dtos are also defined on the client side. Pages are mostly used from https://freefrontend.com.

|Create basket | Catalog | Category view| Basket view
| -------------| ------------- | ------------- | ------------- 
|![](readme-resource/images/ui-1.png)|![](readme-resource/images/ui-2.png)|![](readme-resource/images/ui-3.png)|![](readme-resource/images/ui-4.png)
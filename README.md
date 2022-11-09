# Fibabank-Bootcamp

In this project, I implemented a basic e-commerce project with microservice architecture on java-spring.

## 1-)Project Overview

There are 3 microservices including API-Gateway. These are Inventory Service, Shopping Service and Gate Way. Each service has tradetional 3-layered architecture. I also created a client with Angular. I will write each service's routes here details will be in their section.

### 1-a) Inventory Service

The Inventory service has **_"/inventory"_** prefix for the following routes.

| Method Type  | Route                         | Parameters             |Description | Curl example
| -------------| -------------                 | -------------      | ------------- |------------- |
| _GET_        | **/product/{productId}** |Only path variable. |Returns product with given _prdouctId_ if exist.|curl http://localhost:8080/commerce/inventory/product/18|
| _GET_        | **/products/{categoryId}** |Only path varible. |Returns prodcuts under category for given _categoryId_ if exist.|
| _POST_       | **/product/add** |Prodcut object in request body.|Returns product addition status. Category must exist beforehand.|
| _DELETE_     | **/product/delete/{productId}** |Only path variable|Returns deletion status.|
| _GET_        | **/categories** |No parameter.|Returns all categeries.|
| _GET_        | **/category/{categoryId}** |Only path variable.|Returns category for given _categoryId_.|
| _POST_        | **/category/add** |Category object in request body.|Returns category addition status.|
| _DELETE_        | **/category/delete/{categoryId}** |Only path variable.|Returns deletion status.|

### 1-b) Shopping Service

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


package com.fiba.apigateway.services.components;

import org.springframework.stereotype.Component;

@Component
public class ShoppingComponent {
    private final String shoppingServiceUrl = "http://localhost:8082/shopping";
}

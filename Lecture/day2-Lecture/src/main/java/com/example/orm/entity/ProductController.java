package com.example.orm.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/insert")
    @ResponseBody
    public String insertProduct() {
        Product product = new Product(0,"Phone",125);
        productRepository.save(product);
        return "successfully inserted: "+product.getProductId();
    }
    @GetMapping("/list")
    @ResponseBody
    public Iterable<Product> listProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/list-min")
    @ResponseBody
    public List<Product> listProductsByMin(){
        double minPrice = 150;
        return productRepository.findAllByPriceMin(minPrice);
    }
}
package com.example.orm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String name;
    private double price;

    public Product(long productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

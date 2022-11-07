package com.fiba.shoppingservice.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
    private String customerName;
    private double totalAmount;
    private int status;
    @OneToMany(mappedBy="cart",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<CartProduct> cartProducts;

    public Cart(String customerName) {
        this.customerName = customerName;
    }
}
